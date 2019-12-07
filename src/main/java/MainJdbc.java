import model.House;
import repository.HouseRepositoryJdbcImpl;
import service.HouseService;
import util.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class MainJdbc {

    public static void main(String[] args) throws SQLException {
        HouseService houseService = new HouseService();
        System.out.println("Получил данные из базы:");
        System.out.println(houseService.getData());
        System.out.println("Отсортировал данные по цене:");
        System.out.println(houseService.sortByPrice());
        System.out.println("Отсортировал по метро:");
        System.out.println(houseService.sortByUnderground());
        System.out.println("Поиск по цене:");
        System.out.println(houseService.searchByPrice(1000000, 15000000));
        System.out.println("Поиск по метро:");
        System.out.println(houseService.searchByUnderground("test"));


    }
}
