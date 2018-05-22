package pl.jkan.carrental.carcatalog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateCarRequest {

    String name;
    String description;

    public CreateCarRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
