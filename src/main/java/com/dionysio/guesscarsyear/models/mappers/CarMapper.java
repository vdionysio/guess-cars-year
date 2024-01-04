package com.dionysio.guesscarsyear.models.mappers;

import com.dionysio.guesscarsyear.controllers.dtos.CarDto;
import com.dionysio.guesscarsyear.models.entities.Car;

public class CarMapper {
  public static Car DtoToCar(CarDto carDto) {
    return new Car(carDto.brand(), carDto.model(), carDto.year(), carDto.img());
  }

}
