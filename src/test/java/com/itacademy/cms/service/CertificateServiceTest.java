package com.itacademy.cms.service;

import com.itacademy.cms.exeption.EntityNotFoundException;
import com.itacademy.cms.model.Certificate;
import com.itacademy.cms.repository.CertificateRepository;
import com.itacademy.cms.service.impl.CertificateServiceImpl;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class CertificateServiceTest {

  @Mock
  CertificateRepository certificateRepository;
  @InjectMocks
  CertificateServiceImpl certificateServiceImpl;

  @Test
  void findAllTest() {
    Assertions.assertThrows(EntityNotFoundException.class,
        () -> certificateServiceImpl.findAll());
    Mockito.verify(certificateRepository).findAll();
  }


  @Test
  void findByIdTestExpectedException() {
    String uuid = UUID.randomUUID().toString();
    Assertions.assertThrows(EntityNotFoundException.class,
        () -> certificateServiceImpl.findByUuid(uuid));
    Mockito.verify(certificateRepository).findByUuid(uuid);
  }

  @Test
  void findByIdTestExpectedCertificate() {
    String uuid = UUID.randomUUID().toString();

    Certificate certificate = new Certificate();
    certificate.setUuid(uuid);
    certificate.setName("test");

    Optional<Certificate> optionalCertificate = Optional.of(certificate);

    Mockito.when(certificateRepository.findByUuid(uuid)).thenReturn(optionalCertificate);
    Certificate savedCertificate = certificateServiceImpl.findByUuid(uuid);

    Assertions.assertEquals(certificate.getUuid(), savedCertificate.getUuid());
    Assertions.assertEquals(certificate.getName(), savedCertificate.getName());
  }
}