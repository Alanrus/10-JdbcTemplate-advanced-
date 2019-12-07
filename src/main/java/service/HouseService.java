package service;

import model.House;
import util.JdbcTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HouseService {
    public List<House> getData() throws SQLException {
        List<House> houses = JdbcTemplate.<House>executeQuery(
                "jdbc:sqlite:MyDB",
                "SELECT id, price, district, underground FROM houses",
                resultSet -> new House(
                        resultSet.getInt("id"),
                        resultSet.getInt("price"),
                        resultSet.getString("district"),
                        resultSet.getString("underground")
                )
        );

        return houses;
    }

    public List<House> sortByPrice() throws SQLException {
        List<House> houses = getData();
        houses.sort(((o1, o2) -> o1.getPrice() - o2.getPrice()));
        return houses;
    }

    public List<House> sortByUnderground() throws SQLException {
        List<House> houses = getData();
        houses.sort((o1, o2) -> o1.getUnderground().compareTo(o2.getUnderground()));
        return houses;

    }

    public List<House> searchByPrice(int min, int max) throws SQLException {
        List<House> houses = sortByPrice();
        List<House> result = new ArrayList<>();
        for (House house : houses) {
            if (house.getPrice() <= max && house.getPrice() >= min) {
                result.add(house);
            }
        }
        return result;
    }

    public List<House> searchByUnderground(String metro) throws SQLException {
        List<House> houses = sortByUnderground();
        List<House> result = new ArrayList<>();
        for (House house : houses) {
            if (house.getUnderground().contains(metro)) {
                result.add(house);
            }
        }
        return result;
    }
}
