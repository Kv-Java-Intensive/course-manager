package com.itacademy.cms.service;

import com.itacademy.cms.exeption.CertificateNotFoundException;
import com.itacademy.cms.repository.CertificateRepository;
import com.itacademy.cms.service.impl.CertificateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;


@ExtendWith(MockitoExtension.class)
public class CertificateServiceTest {

  CertificateServiceImpl certificateServiceImpl;
  @Autowired
  private CertificateRepository certificateRepository;

  @Test
  void findAllTest() {
    Assertions.assertThrows(CertificateNotFoundException.class,
        () -> certificateServiceImpl.findAll());
    Mockito.verify(certificateRepository).findAll();
  }


  @Test
  void findByIdTestExpectedException() {
    Long id = 1L;
    Assertions.assertThrows(CertificateNotFoundException.class,
        () -> certificateServiceImpl.findById(id));
    Mockito.verify(certificateRepository).findById(id);
  }

//  @Test
//  void findByIdTestExpectedCertificate() {
//    Long id = 1L;
//
//    Certificate certificate = new Certificate();
//    certificate.setId(id);
//    certificate.setName("test");
//
//    Optional<Certificate> optionalCertificate = Optional.of(certificate);
//
//    Mockito.when(certificateRepository.findById(id)).thenReturn(optionalCertificate);
//    Certificate savedCertificate = certificateServiceImpl.findById(id);
//
//    Assertions.assertEquals(certificate.getId(), savedCertificate.getId());
//    Assertions.assertEquals(certificate.getName(), savedCertificate.getName());
//  }
}