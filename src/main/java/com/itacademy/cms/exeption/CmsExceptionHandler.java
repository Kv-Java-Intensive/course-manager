package com.itacademy.cms.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CmsExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorResponse> entityNotFoundException(EntityNotFoundException exception) {
    ErrorResponse error = new ErrorResponse(exception.getMessage(), HttpStatus.NO_CONTENT,
        HttpStatus.NO_CONTENT.value());
    return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
  }

  @ExceptionHandler(ParameterMissingException.class)
  public ResponseEntity<ErrorResponse> parameterMissingException(
      ParameterMissingException exception) {
    ErrorResponse error =
        new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST,
            HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}
