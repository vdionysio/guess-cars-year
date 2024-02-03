package com.dionysio.guesscarsyear.controllers.advice.Exceptions;

public class DuplicatedIdException extends RuntimeException {

  public DuplicatedIdException(String message) {
    super(message);
  }
}
