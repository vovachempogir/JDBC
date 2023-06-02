package dao;

import model.City;
import java.util.*;

public interface CityDAO {
    void create(City city);

    City findById(Integer id);

    List<City> findAll();

    void update(City city);

    void deleteById(Integer id);
}
