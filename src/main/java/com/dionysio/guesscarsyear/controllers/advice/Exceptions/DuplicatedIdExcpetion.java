package com.dionysio.guesscarsyear.controllers.advice.Exceptions;

public class DuplicatedIdExcpetion extends RuntimeException {

  public DuplicatedIdExcpetion(String message) {
    super(message);
  }
}
