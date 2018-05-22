package pl.jkan.carrental.carcatalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jkan.carrental.carcatalog.dto.CarDetailsDto;
import pl.jkan.carrental.carcatalog.dto.CreateCarRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
class CarCatalogController {

    @Autowired
    CarDetailsRepository repository;

    @GetMapping(value = "/cars/{carId}")
    ResponseEntity<CarDetailsDto> detailsForCar(@PathVariable String carId) {

        return repository.findById(carId)
                .map(car -> car.snapschot())
                .map(dto -> ResponseEntity.ok(dto))
                .orElseThrow(()-> new NotFoundException());

    }

    @GetMapping("/cars")
    List<CarDetailsDto> list() {
        List<CarDetailsDto> list = new ArrayList<>();

        repository.findAll()
                .iterator()
                .forEachRemaining(car -> list.add(car.snapschot()));

        return list;
    }

    @PostMapping("/cars/{carId}")
    ResponseEntity car(@PathVariable String carId, @RequestBody CreateCarRequest r) {

        repository.save(CarDetails.withCreateRequest(carId, r));

        return ResponseEntity.noContent().build();

    }

}