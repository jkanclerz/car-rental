package pl.jkan.carrental.carcatalog;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.jkan.carrental.carcatalog.dto.CarDetailsDto;
import pl.jkan.carrental.carcatalog.dto.CreateCarRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
class CarDetails {
    @Id
    private String id;
    private String name;
    private String description;

    public CarDetails(String carId, String carName, String carDescription) {
        id = carId;
        name = carName;
        description = carDescription;
    }

    public static CarDetails withCreateRequest(String carId, CreateCarRequest r) {
        return new CarDetails(carId, r.getName(), r.getDescription());
    }

    public CarDetailsDto snapschot() {
        return new CarDetailsDto(id, name, description);
    }
}
