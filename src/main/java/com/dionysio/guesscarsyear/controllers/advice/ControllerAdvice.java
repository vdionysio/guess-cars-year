package com.dionysio.guesscarsyear.controllers.advice;

import com.dionysio.guesscarsyear.controllers.advice.Exceptions.DuplicatedIdException;
import com.dionysio.guesscarsyear.controllers.advice.Exceptions.GuessLimitException;
import com.dionysio.guesscarsyear.controllers.advice.Exceptions.InsufficientRecordsException;
import com.dionysio.guesscarsyear.controllers.advice.Exceptions.InvalidIdException;
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerAdvice {

  @ExceptionHandler(InsufficientRecordsException.class)
  @ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
  public ErrorMessage insufficientRecordsException(InsufficientRecordsException ex,
      WebRequest request) {

    return new ErrorMessage(
        HttpStatus.EXPECTATION_FAILED.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
  }

  @ExceptionHandler(DuplicatedIdException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorMessage duplicatedIdExcpetion(DuplicatedIdException ex,
      WebRequest request) {

    return new ErrorMessage(
        HttpStatus.BAD_REQUEST.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
  }

  @ExceptionHandler(InvalidIdException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorMessage invalidIdException(InvalidIdException ex, WebRequest request) {
    return new ErrorMessage(
        HttpStatus.NOT_FOUND.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
  }

  @ExceptionHandler(GuessLimitException.class)
  @ResponseStatus(value = HttpStatus.CONFLICT)
  public ErrorMessage guessLimitException(GuessLimitException ex, WebRequest request) {
    return new ErrorMessage(
        HttpStatus.CONTINUE.value(),
        new Date(),
        "Game must contains only 5 guesses.",
        request.getDescription(false));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ErrorMessage methodArgumentNotValidException(MethodArgumentNotValidException ex,
      WebRequest request) {

    BindingResult bindingResult = ex.getBindingResult();
    List<FieldError> fieldErrors = bindingResult.getFieldErrors();

    // Extract the validation errors and create a custom message
    StringBuilder errorMessage = new StringBuilder("Validation failed: ");
    for (FieldError fieldError : fieldErrors) {
      errorMessage.append(fieldError.getDefaultMessage()).append("; ");
    }
    return new ErrorMessage(
        HttpStatus.BAD_REQUEST.value(),
        new Date(),
        errorMessage.toString(),
        request.getDescription(false)
    );
  }
}
