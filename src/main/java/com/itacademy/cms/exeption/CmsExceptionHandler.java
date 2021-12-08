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
    ErrorResponse error = new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND, 404);

    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ParameterMissingException.class)
  public ResponseEntity<ErrorResponse> parameterMissingException(
      ParameterMissingException exception) {
    ErrorResponse error =
        new ErrorResponse(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY, 422);

    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
