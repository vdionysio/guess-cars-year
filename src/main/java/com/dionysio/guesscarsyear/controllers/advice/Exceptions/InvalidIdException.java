package com.dionysio.guesscarsyear.controllers.advice.Exceptions;

public class InvalidIdException extends RuntimeException {
  public InvalidIdException(String message) {
    super(message);
  }
}
