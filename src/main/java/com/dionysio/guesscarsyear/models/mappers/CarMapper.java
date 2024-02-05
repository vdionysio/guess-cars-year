package com.dionysio.guesscarsyear.models.mappers;

import com.dionysio.guesscarsyear.controllers.dtos.CarBasicDto;
import com.dionysio.guesscarsyear.controllers.dtos.CarDto;
import com.dionysio.guesscarsyear.models.entities.Car;

public class CarMapper {
  public static Car dtoToCar(CarDto carDto) {
    return new Car(carDto.brand(), carDto.model(), carDto.year(), carDto.img());
  }

  public static CarBasicDto carToBasicDto(Car car) {
    return new CarBasicDto(car.getId());
  }
}
