package com.dionysio.guesscarsyear.controllers.advice.Exceptions;

public class InsufficientRecordsException extends RuntimeException{

  public InsufficientRecordsException(String message) {
    super(message);
  }
}
