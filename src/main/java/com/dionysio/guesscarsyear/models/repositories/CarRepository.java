package com.dionysio.guesscarsyear.models.repositories;

import com.dionysio.guesscarsyear.models.entities.Car;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

  Page<Car> findAll(Pageable pageable);

  Optional<Car> findByBrandAndModelAndYear(String brand, String model, int year);
}
