package pl.jkan.carrental.carcatalog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CarDetailsDto {
    private String id;
    private String name;
    private String description;

    public CarDetailsDto(String carId, String carName, String carDescription) {
        id = carId;
        name = carName;
        description = carDescription;
    }
}
