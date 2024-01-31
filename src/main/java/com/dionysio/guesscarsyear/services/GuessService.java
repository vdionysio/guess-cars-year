package com.dionysio.guesscarsyear.services;

import com.dionysio.guesscarsyear.controllers.advice.Exceptions.InvalidIdException;
import com.dionysio.guesscarsyear.controllers.dtos.GuessDto;
import com.dionysio.guesscarsyear.controllers.dtos.GuessFormDto;
import com.dionysio.guesscarsyear.models.entities.Car;
import com.dionysio.guesscarsyear.models.entities.Guess;
import com.dionysio.guesscarsyear.models.mappers.GuessMapper;
import com.dionysio.guesscarsyear.models.repositories.CarRepository;
import com.dionysio.guesscarsyear.models.repositories.GuessRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class GuessService {

  private static final int MAX_SCORE = 1000;
  private static final double PROPORTION_UNTIL_2_YEARS = 0.9;
  private static final double PROPORTION_UNTIL_5_YEARS = 0.8;
  private static final double PROPORTION_UNTIL_10_YEARS = 0.6;
  private static final double PROPORTION_UNTIL_20_YEARS = 0.4;
  private final GuessRepository guessRepository;
  private final CarRepository carRepository;


  public GuessService(GuessRepository guessRepository, CarRepository carRepository) {
    this.guessRepository = guessRepository;
    this.carRepository = carRepository;
  }

  public GuessDto guessCarYear(GuessFormDto guessForm) {
    Optional<Car> optionalCar = carRepository.findById(guessForm.carId());

    if (optionalCar.isEmpty()) {
      throw new InvalidIdException("There isn't any car with the given id.");
    }

    Guess guess = GuessMapper.formDtoToGuess(guessForm, optionalCar.get());

    calculateScore(guess);
    guessRepository.save(guess);

    return GuessMapper.guessToDto(guess);
  }

  private void calculateScore(Guess guess) {
    int carYear = guess.getCar().getYear();
    int guessYear = guess.getGuessYear();

    int difference = Math.abs(guessYear - carYear);

    if (difference > 20) {
      guess.setScore(0);
    } else {
      if (difference == 0) {
        guess.setScore(MAX_SCORE);
      } else if (difference <= 2) {
        guess.setScore((int) (MAX_SCORE * PROPORTION_UNTIL_2_YEARS));
      } else if (difference <= 5) {
        guess.setScore((int) (MAX_SCORE * PROPORTION_UNTIL_5_YEARS));
      } else if (difference <= 10) {
        guess.setScore((int) (MAX_SCORE * PROPORTION_UNTIL_10_YEARS));
      } else {
        guess.setScore((int) (MAX_SCORE * PROPORTION_UNTIL_20_YEARS));
      }
    }
  }
}
