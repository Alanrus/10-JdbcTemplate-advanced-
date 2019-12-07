package repository;

import exception.DataAccessException;
import model.House;

import java.sql.*;
import java.util.Collection;
import java.util.Optional;

public class HouseRepositoryJdbcImpl implements HouseRepository {
    public final String url;

    public HouseRepositoryJdbcImpl(String url) {
        this.url = url;
    }

    public House save(House house) {
        return house.getId() == 0 ? insert(house) : update(house);
    }

    @Override
    public Optional<House> getById(int id) {
        String sql = "SELECT id, price, district, underground FROM houses WHERE id = ?";
        try (
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery();) {
                if (resultSet.next()) {
                    return Optional.of(new House(
                            resultSet.getInt("id"),
                            resultSet.getInt("price"),
                            resultSet.getString("district"),
                            resultSet.getString("underground")
                    ));
                }
                return Optional.empty();

            }
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    //TODO make private
    private House insert(House house) {
        String sql = "INSERT INTO houses (price, district, underground) VALUES (?, ?, ?);";
        try (
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            int index = 1;
            statement.setInt(index++, house.getPrice());
            statement.setString(index++, house.getDistrict());
            statement.setString(index++, house.getUnderground());
            statement.executeUpdate();
            System.out.println("Данные добавлены");
            return house;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    private House update(House house) {
        String sql = "UPDATE houses SET price = ?, district = ?, underground = ? WHERE id = ?;";
        try (
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            int index = 1;
            statement.setInt(index++, house.getPrice());
            statement.setString(index++, house.getDistrict());
            statement.setString(index++, house.getUnderground());
            statement.setInt(index++, house.getId());
            statement.executeUpdate();
            System.out.println("Данные обновлены");
            return house;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public Optional<House> delete(int id) {
        String sql = "DELETE FROM houses WHERE id = ?;";
        try (
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            if (new HouseRepositoryJdbcImpl(url).getById(id) != this.getById(id)) {
                statement.executeUpdate();
                System.out.println("Данные удалены");
                return Optional.empty();
            } else System.out.println("Данные не удалены");
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
        return Optional.empty();
    }
}

















