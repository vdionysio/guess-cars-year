package com.dionysio.guesscarsyear.services;

import com.dionysio.guesscarsyear.controllers.advice.Exceptions.InsufficientRecordsException;
import com.dionysio.guesscarsyear.controllers.advice.Exceptions.InvalidIdException;
import com.dionysio.guesscarsyear.controllers.dtos.GameDto;
import com.dionysio.guesscarsyear.models.entities.Game;
import com.dionysio.guesscarsyear.models.mappers.GameMapper;
import com.dionysio.guesscarsyear.models.repositories.GameRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class GameService {

  private final GameRepository gameRepository;

  public GameService(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  public Long createNewGame() {
    Game newGame = gameRepository.save(new Game());
    return newGame.getId();
  }

  public GameDto getGame(Long gameId) {
    Optional<Game> optionalGame = gameRepository.findById(gameId);

    if (optionalGame.isEmpty()) {
      throw new InvalidIdException("There isn't any game with the given id.");
    }

    if (optionalGame.get().getGuesses().size() != 5) {
      throw new InsufficientRecordsException("This game is not completed yet.");
    }

    return GameMapper.gameToDto(optionalGame.get());
  }
}
