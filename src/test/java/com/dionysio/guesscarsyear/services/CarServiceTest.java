package com.dionysio.guesscarsyear.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import com.dionysio.guesscarsyear.controllers.advice.Exceptions.DuplicatedIdExcpetion;
import com.dionysio.guesscarsyear.controllers.advice.Exceptions.InsufficientRecordsException;
import com.dionysio.guesscarsyear.controllers.dtos.CarDto;
import com.dionysio.guesscarsyear.models.entities.Car;
import com.dionysio.guesscarsyear.models.mappers.CarMapper;
import com.dionysio.guesscarsyear.models.repositories.CarRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

  @InjectMocks
  CarService carService;

  @Mock
  CarRepository carRepository;

  @Test
  @DisplayName("Add car with fresh entries will successfully save in db")
  void testAddCar_WithFreshEntries_SuccessfullySave() {
    Mockito.when(carRepository.findByBrandAndModelAndYear("Ford", "Fiesta", 2013)).thenReturn(
        Optional.empty());
    CarDto carDto = new CarDto("Ford", "Fiesta", 2013, "##");
    carService.addCar(carDto);
    Mockito.verify(carRepository).save(any());
  }

  @Test
  @DisplayName("Add car with duplicated entries will throw an exception")
  void testAddCar_WithDuplicatedEntries_ThrowException() {
    CarDto carDto = new CarDto("Ford", "Fiesta", 2013, "##");
    Mockito.when(carRepository.findByBrandAndModelAndYear("Ford", "Fiesta", 2013)).thenReturn(
        Optional.of(CarMapper.DtoToCar(carDto)));

    assertThrows(DuplicatedIdExcpetion.class, () -> {
      carService.addCar(carDto);
    });
  }

  @Test
  @DisplayName("Get 5 Random Cars with a valid amount will get 5 different cars")
  void testGet5RandomCars_WithValidAmount_SetOfCars() {
    Mockito.when(carRepository.count()).thenReturn(10L);
    Mockito.when(carRepository.findAll(any(PageRequest.class))).thenAnswer(I -> mockPage());

    Set<Car> result = carService.getFiveRandomCars();
    assertNotNull(result);
    assertEquals(5, result.size());
  }

  private Page<Car> mockPage() {
    int idx = (int) (Math.random() * 10);
    List<Car> carList = Arrays.asList(
        new Car("BrandX", "Model1", 2023, "##"),
        new Car("BrandX", "Model2", 2023, "##"),
        new Car("BrandX", "Model3", 2023, "##"),
        new Car("BrandX", "Model4", 2023, "##"),
        new Car("BrandY", "Model5", 2023, "##"),
        new Car("BrandY", "Model1", 2023, "##"),
        new Car("BrandY", "Model2", 2023, "##"),
        new Car("BrandY", "Model3", 2023, "##"),
        new Car("BrandZ", "Model1", 2023, "##"),
        new Car("BrandZ", "Model2", 2023, "##")
    );

    // Create a mock Page<Car> with the carList and a PageRequest
    return new PageImpl<>(Collections.singletonList(carList.get(idx)), PageRequest.of(0, 1), 1);
  }


  @Test
  @DisplayName("Get 5 Random Cars when there is less than 5 in the DB will throw an exception")
  void testGet5RandomCars_WithInsufficientAmount_ThrowExcpetion() {
    Mockito.when(carRepository.count()).thenReturn(2L);

    assertThrows(InsufficientRecordsException.class, () -> {
      carService.getFiveRandomCars();
    });
  }
}