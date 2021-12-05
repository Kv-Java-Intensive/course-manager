package com.itacademy.cms.exeption;

public class CertificateNotFoundException extends Exception {
  public CertificateNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}