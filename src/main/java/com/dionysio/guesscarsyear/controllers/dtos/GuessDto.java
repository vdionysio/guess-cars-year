package com.dionysio.guesscarsyear.controllers.dtos;

public record GuessDto(long id, int guessYear, int score, long carId, long gameId, int realYear) {

}
