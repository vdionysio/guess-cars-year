package com.dionysio.guesscarsyear.controllers;

import com.dionysio.guesscarsyear.models.entities.Car;
import com.dionysio.guesscarsyear.models.repositories.CarRepository;
import java.net.URI;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imgs")
public class ImgController {

  private final CarRepository carRepository;

  public ImgController(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  @GetMapping("/{carId}")
  public ResponseEntity<Void> redirectImage(@PathVariable Long carId) {
    Optional<Car> optionalCar = carRepository.findById(carId);

    return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(optionalCar.get().getImg()))
        .build();
  }
}
