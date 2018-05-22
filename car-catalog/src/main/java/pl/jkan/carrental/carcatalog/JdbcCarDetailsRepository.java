package pl.jkan.carrental.carcatalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.jkan.carrental.carcatalog.CarDetails;

import java.util.Optional;

@Repository
class JdbcCarDetailsRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Optional<CarDetails> findById(String id) {
        return Optional.of(jdbcTemplate.queryForObject(
                "select * from car_details where id=?",
                new Object[] { id },
                new BeanPropertyRowMapper<>(CarDetails.class)
        ));
    }

    public void deleteById(String id) {
        jdbcTemplate.update(
            "delete from car_details where id=?",
                new Object[] { id }
        );
    }

    public void create(CarDetails carDetails) {
        jdbcTemplate.update(
            "insert into car_details (id, name, description) " + "values(?,  ?, ?)",
            new Object[] { carDetails.getId(), carDetails.getName(), carDetails.getDescription() }
        );
    }

    public void update(CarDetails carDetails) {
        jdbcTemplate.update(
            "update car_details " + " set name = ?, description = ? " + " where id = ?",
            new Object[] { carDetails.getName(), carDetails.getDescription(), carDetails.getId() }
        );
    }
}
