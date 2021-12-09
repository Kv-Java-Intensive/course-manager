package com.itacademy.cms.exeption;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorResponse {
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private Date timestamp;

  private String status;

  private String message;

  private int code;

  public ErrorResponse(String message, HttpStatus httpStatus, int code) {
    this.timestamp = new Date();
    this.status = httpStatus.name();
    this.message = message;
    this.code = code;
  }
}
