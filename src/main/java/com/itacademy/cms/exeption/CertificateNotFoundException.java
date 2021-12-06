package com.itacademy.cms.exeption;

public class CertificateNotFoundException extends RuntimeException {
  public CertificateNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}