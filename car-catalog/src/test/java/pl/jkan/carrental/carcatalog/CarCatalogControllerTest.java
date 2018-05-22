package pl.jkan.carrental.carcatalog;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jkan.carrental.ApplicationTest;
import pl.jkan.carrental.carcatalog.dto.CarDetailsDto;
import pl.jkan.carrental.carcatalog.dto.CreateCarRequest;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
    classes = {ApplicationTest.class},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class CarCatalogControllerTest {

    private static final String ANY_CAR_ID = "toyota-chr-hybrid-01";
    public static final String ANY_CAR_NAME = "Toyota CHR";

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    CarDetailsRepository repository;

    @Test
    public void should_show_details_of_created_car() {

        testRestTemplate.postForEntity(
                "/cars/" + ANY_CAR_ID,
                new CreateCarRequest(ANY_CAR_NAME, "Some Desc"),
                ResponseEntity.class
        );

        ResponseEntity res = testRestTemplate.getForEntity(
                "/cars/" + ANY_CAR_ID,
                CarDetails.class
        );

        assertThat(res.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(res.getBody()).isInstanceOf(CarDetails.class);

        CarDetails dto = (CarDetails)res.getBody();
        assertThat(dto.getName()).isEqualTo("Toyota CHR");
    }

    @Test
    public void allow_list_all_possible_cars() {

        repository.save(new CarDetails("toyota-1", "CHR", "cross"));
        repository.save(new CarDetails("toyota-2", "RAV-4", "SUV"));

        ResponseEntity res = testRestTemplate.getForEntity(
                "/cars",
                List.class
        );

        assertThat(res.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(res.getBody()).isInstanceOf(List.class);
        assertThat(((List<CarDetailsDto>) res.getBody()).size()).isEqualTo(2);
    }
}