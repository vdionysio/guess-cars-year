package com.dionysio.guesscarsyear.services;

import com.dionysio.guesscarsyear.controllers.advice.Exceptions.DuplicatedIdException;
import com.dionysio.guesscarsyear.controllers.advice.Exceptions.InsufficientRecordsException;
import com.dionysio.guesscarsyear.controllers.dtos.CarDto;
import com.dionysio.guesscarsyear.models.entities.Car;
import com.dionysio.guesscarsyear.models.mappers.CarMapper;
import com.dionysio.guesscarsyear.models.repositories.CarRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CarService {

  private final CarRepository carRepository;

  public CarService(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  public void addCar(CarDto carDto) {
    Optional<Car> optionalCar = carRepository.findByBrandAndModelAndYear(
        carDto.brand(), carDto.model(), carDto.year());
    if (optionalCar.isPresent()) {
      throw new DuplicatedIdException("This combination of brand, model and year already exists");
    }
    carRepository.save(CarMapper.DtoToCar(carDto));
  }

  public Car randomCar() {
    long qty = carRepository.count();
    int idx = (int) (Math.random() * qty);
    Page<Car> carPage = carRepository.findAll(PageRequest.of(idx, 1));
    Car c = null;
    if (carPage.hasContent()) {
      c = carPage.getContent().get(0);
    }
    return c;
  }

  public Set<Car> getFiveRandomCars() {
    long qty = carRepository.count();
    Set<Car> randomCars = new HashSet<>();

    if (qty < 5) {
      throw new InsufficientRecordsException("Sorry, there are less than 5 cars in the database");
    } else {
      do {

        randomCars.add(randomCar());
      } while (randomCars.size() < 5);
    }

    return randomCars;
  }
}
