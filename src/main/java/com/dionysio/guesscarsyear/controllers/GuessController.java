package com.dionysio.guesscarsyear.controllers;

import com.dionysio.guesscarsyear.controllers.dtos.GuessDto;
import com.dionysio.guesscarsyear.controllers.dtos.GuessFormDto;
import com.dionysio.guesscarsyear.controllers.dtos.ResponseDto;
import com.dionysio.guesscarsyear.services.GuessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guesses")
public class GuessController {

  private final GuessService guessService;

  public GuessController(GuessService guessService) {
    this.guessService = guessService;
  }

  @PostMapping
  @CrossOrigin(origins = "http://localhost:5173")
  public ResponseEntity<ResponseDto> guessCarYear(@RequestBody GuessFormDto guessFormDto) {
    GuessDto guessDto = guessService.guessCarYear(guessFormDto);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto("Guess recorded", guessDto));
  }
}
