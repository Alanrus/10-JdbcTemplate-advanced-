import model.House;
import repository.HouseRepositoryJdbcImpl;

public class MainJdbcPreparedStatement {
    public static void main(String[] args) {

        HouseRepositoryJdbcImpl houseRepositoryJdbc = new HouseRepositoryJdbcImpl("jdbc:sqlite:MyDB");

        House testHouse = houseRepositoryJdbc.save(new House(0, 111111, "тест", "тестовое метро"));
        houseRepositoryJdbc.save(new House(13, 9999, "qwer", "qwer"));
        houseRepositoryJdbc.delete(13);
        System.out.println(houseRepositoryJdbc.getById(15));
    }
}
