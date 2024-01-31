package com.dionysio.guesscarsyear.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Guess {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int guessYear;

  private int score;

  @ManyToOne
  @JoinColumn(name = "car_id")
  private Car car;

  public Guess() {
  }

  public Guess(int guessYear, Car car) {
    this.guessYear = guessYear;
    this.car = car;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getGuessYear() {
    return guessYear;
  }

  public void setGuessYear(int guessYear) {
    this.guessYear = guessYear;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }
}
