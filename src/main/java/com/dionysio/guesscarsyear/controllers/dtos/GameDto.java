package com.dionysio.guesscarsyear.controllers.dtos;

public record GameDto(Long gameId, Long guess1, Long guess2, Long guess3, Long guess4, Long guess5,
                      int finalScore) {

}
