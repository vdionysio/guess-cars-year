package com.dionysio.guesscarsyear.services;

import com.dionysio.guesscarsyear.controllers.advice.Exceptions.GuessLimitException;
import com.dionysio.guesscarsyear.controllers.advice.Exceptions.InvalidIdException;
import com.dionysio.guesscarsyear.controllers.dtos.GuessDto;
import com.dionysio.guesscarsyear.controllers.dtos.GuessFormDto;
import com.dionysio.guesscarsyear.models.entities.Car;
import com.dionysio.guesscarsyear.models.entities.Game;
import com.dionysio.guesscarsyear.models.entities.Guess;
import com.dionysio.guesscarsyear.models.mappers.GuessMapper;
import com.dionysio.guesscarsyear.models.repositories.CarRepository;
import com.dionysio.guesscarsyear.models.repositories.GameRepository;
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

  private final GameRepository gameRepository;


  public GuessService(GuessRepository guessRepository, CarRepository carRepository,
      GameRepository gameRepository) {
    this.guessRepository = guessRepository;
    this.carRepository = carRepository;
    this.gameRepository = gameRepository;
  }

  public GuessDto guessCarYear(GuessFormDto guessForm) {
    Optional<Car> optionalCar = carRepository.findById(guessForm.carId());

    if (optionalCar.isEmpty()) {
      throw new InvalidIdException("There isn't any car with the given id.");
    }

    Optional<Game> optionalGame = gameRepository.findById(guessForm.gameId());

    if (optionalGame.isEmpty()) {
      throw new InvalidIdException("There isn't any game with the given id.");
    }

    if (optionalGame.get().getGuesses().size() == 5) {
      throw new GuessLimitException();
    }

    Guess guess = GuessMapper.formDtoToGuess(guessForm, optionalCar.get(), optionalGame.get());

    calculateScore(guess);
    guessRepository.save(guess);

    return GuessMapper.guessToDto(guess);
  }

  private void calculateScore(Guess guess) {
    int carYear = guess.getCar().getYear();
    int guessYear = guess.getGuessYear();

    int difference = Math.abs(guessYear - carYear);

    if (difference > 15) {
      guess.setScore(0);
    } else {
      if (difference == 0) {
        guess.setScore(MAX_SCORE);
      } else if (difference <= 2) {
        guess.setScore((int) (MAX_SCORE * (PROPORTION_UNTIL_2_YEARS - (difference * 0.01))));
      } else if (difference <= 5) {
        guess.setScore((int) (MAX_SCORE * (PROPORTION_UNTIL_5_YEARS - (difference * 0.01))));
      } else if (difference <= 10) {
        guess.setScore((int) (MAX_SCORE * (PROPORTION_UNTIL_10_YEARS - (difference * 0.01))));
      } else {
        guess.setScore((int) (MAX_SCORE * (PROPORTION_UNTIL_20_YEARS - (difference * 0.01))));
      }
    }

    calculateTotalScore(guess);
  }

  public void calculateTotalScore(Guess guess) {
    guess.getGame().addScore(guess.getScore());
  }
}
