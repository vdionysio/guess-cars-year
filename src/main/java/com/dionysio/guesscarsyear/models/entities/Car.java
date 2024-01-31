package com.dionysio.guesscarsyear.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"brand", "model", "year"}))
public class Car {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String brand;
  private String model;
  private int year;
  @Column(nullable = false)
  private String img;

  @OneToMany(mappedBy = "car")
  private Set<Guess> guesses;

  public Car() {

  }

  public Car(String brand, String model, int year, String img) {
    this.brand = brand;
    this.model = model;
    this.year = year;
    this.img = img;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
