package com.dionysio.guesscarsyear.models.mappers;

import com.dionysio.guesscarsyear.controllers.dtos.GuessDto;
import com.dionysio.guesscarsyear.controllers.dtos.GuessFormDto;
import com.dionysio.guesscarsyear.models.entities.Car;
import com.dionysio.guesscarsyear.models.entities.Guess;

public class GuessMapper {

  public static Guess formDtoToGuess(GuessFormDto guessFormDto, Car car) {
    return new Guess(guessFormDto.guessYear(), car);
  }

  public static GuessDto guessToDto(Guess guess) {
    return new GuessDto(guess.getId(), guess.getGuessYear(), guess.getScore(),
        guess.getCar().getId());
  }
}
