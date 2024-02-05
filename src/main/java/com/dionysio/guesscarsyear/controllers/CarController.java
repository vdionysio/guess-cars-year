package com.dionysio.guesscarsyear.controllers;

import com.dionysio.guesscarsyear.controllers.dtos.CarBasicDto;
import com.dionysio.guesscarsyear.controllers.dtos.CarDto;
import com.dionysio.guesscarsyear.controllers.dtos.ResponseDto;
import com.dionysio.guesscarsyear.models.entities.Car;
import com.dionysio.guesscarsyear.services.CarService;
import jakarta.validation.Valid;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {

  private final CarService carService;

  public CarController(CarService carService) {
    this.carService = carService;
  }

  @PostMapping
  public ResponseEntity<ResponseDto> addCar(@Valid @RequestBody CarDto carDto) {
    carService.addCar(carDto);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto("New car added", carDto));
  }

  @GetMapping
  @CrossOrigin(origins = "http://localhost:5173")
  public ResponseEntity<ResponseDto> getFiveRandomCars() {
    Set<CarBasicDto> randomCars = carService.getFiveRandomCars();

    return ResponseEntity.ok(new ResponseDto("Five random cars got from db", randomCars));
  }
}
