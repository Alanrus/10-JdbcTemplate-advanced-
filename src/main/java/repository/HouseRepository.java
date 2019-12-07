package repository;

import model.House;

import java.util.Optional;

public interface HouseRepository {
    House save(House house);

    Optional<House> getById(int id);
}
