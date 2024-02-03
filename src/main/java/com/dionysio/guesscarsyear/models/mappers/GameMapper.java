package com.dionysio.guesscarsyear.models.mappers;

import com.dionysio.guesscarsyear.controllers.dtos.GameDto;
import com.dionysio.guesscarsyear.models.entities.Game;

public class GameMapper {

  public static GameDto gameToDto(Game game) {
    return new GameDto(
        game.getId(),
        game.getGuesses().get(0).getId(),
        game.getGuesses().get(1).getId(),
        game.getGuesses().get(2).getId(),
        game.getGuesses().get(3).getId(),
        game.getGuesses().get(4).getId(),
        game.getFinalScore()
    );
  }
}
