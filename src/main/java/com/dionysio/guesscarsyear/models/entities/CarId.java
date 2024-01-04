package com.dionysio.guesscarsyear.models.entities;

import java.io.Serializable;
import java.util.Objects;

public class CarId implements Serializable {
  private String brand;
  private String model;
  private int year;

  public CarId() {
  }

  public CarId(String brand, String model, int year) {
    this.brand = brand;
    this.model = model;
    this.year = year;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CarId carId)) {
      return false;
    }
    return year == carId.year && brand.equals(carId.brand) && model.equals(carId.model);
  }

  @Override
  public int hashCode() {
    return Objects.hash(brand, model, year);
  }
}
