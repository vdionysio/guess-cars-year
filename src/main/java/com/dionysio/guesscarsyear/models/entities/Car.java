package com.dionysio.guesscarsyear.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import java.util.Objects;

@Entity
@IdClass(CarId.class)
public class Car {

  @Id
  private String brand;
  @Id
  private String model;
  @Id
  private int year;
  @Column(nullable = false)
  private String img;

  public Car() {

  }
  public Car(String brand, String model, int year, String img) {
    this.brand = brand;
    this.model = model;
    this.year = year;
    this.img = img;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Car car)) {
      return false;
    }
    return getYear() == car.getYear() && getBrand().equals(car.getBrand()) && getModel().equals(
        car.getModel()) && getImg().equals(car.getImg());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getBrand(), getModel(), getYear(), getImg());
  }
}
