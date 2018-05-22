package pl.jkan.carrental.carcatalog;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jkan.carrental.ApplicationTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationTest.class})
public class JdbcCarDetailsRepositoryTest {
    @Autowired
    JdbcCarDetailsRepository repository;


    private static final String CAR_ID = "toyota-chr-01";
    private static final String CAR_NAME = "toyota CHR";
    private static final String CAR_OTHER_NAME = "toyota CRH - Hybrid";
    private static final String CAR_DESCRIPTION = "Car for active people";

    @Before
    @After
    public void clearDb() {
        repository.deleteById(CAR_ID);
    }

    @Test
    public void storeCarDetails() {
        repository.create(new CarDetails(CAR_ID, CAR_NAME, CAR_DESCRIPTION));

        Optional<CarDetails> result = repository.findById(CAR_ID);

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isInstanceOf(CarDetails.class);
        assertThat(result.get().getId()).isEqualTo(CAR_ID);
    }

    @Test
    public void itAllowUpdateCarDetails() {
        repository.create(new CarDetails(CAR_ID, CAR_NAME, CAR_DESCRIPTION));
        repository.update(new CarDetails(CAR_ID, CAR_OTHER_NAME, CAR_DESCRIPTION));

        CarDetails result = repository.findById(CAR_ID).get();

        assertThat(result.getName())
                .isNotBlank()
                .isEqualTo(CAR_OTHER_NAME);
    }
}
