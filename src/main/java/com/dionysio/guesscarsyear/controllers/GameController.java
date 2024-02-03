package com.dionysio.guesscarsyear.controllers;

import com.dionysio.guesscarsyear.controllers.dtos.GameDto;
import com.dionysio.guesscarsyear.controllers.dtos.GameIdDto;
import com.dionysio.guesscarsyear.controllers.dtos.ResponseDto;
import com.dionysio.guesscarsyear.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class GameController {

  private final GameService gameService;

  public GameController(GameService gameService) {
    this.gameService = gameService;
  }

  @PostMapping
  public ResponseEntity<ResponseDto> createNewGame() {
    Long newGameId = gameService.createNewGame();
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto("New game created", new GameIdDto(newGameId)));
  }

  @GetMapping("/{gameId}")
  public ResponseEntity<ResponseDto> getGame(@PathVariable Long gameId) {
    GameDto gameDto = gameService.getGame(gameId);

    return ResponseEntity.ok(new ResponseDto("Completed game", gameDto));
  }
}
