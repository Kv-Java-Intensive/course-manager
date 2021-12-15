package com.itacademy.cms.service;

import com.itacademy.cms.exeption.EntityNotFoundException;
import com.itacademy.cms.model.Certificate;
import com.itacademy.cms.model.dto.CertificateDto;
import com.itacademy.cms.repository.CertificateRepository;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@Transactional(Transactional.TxType.REQUIRED)
@ActiveProfiles(profiles = "test")
public class CertificateServiceIntegrationTest {

  @Autowired
  CertificateService certificateService;

  @Autowired
  CertificateRepository certificateRepository;

  @BeforeEach
  void cleanTable() {
    certificateRepository.deleteAll();
  }

  @Test
  void certificateSaveAndFindByIdTest() {
    Certificate savedCertificate = getSavedCertificate();

    Certificate certificateById = certificateService.findByUuid(savedCertificate.getUuid());

    Assertions.assertEquals(savedCertificate.getName(), certificateById.getName());
  }

  @Test
  void certificateDeleteTest() {
    Certificate initCertificate = getSavedCertificate();

    Assertions.assertNotNull(
        certificateService.findByUuid(initCertificate.getUuid()));

    certificateService.deleteCertificateByUuid(initCertificate.getUuid());

    Assertions.assertThrows(EntityNotFoundException.class,
        () -> certificateService.findByUuid(initCertificate.getUuid()));
  }

  private Certificate getSavedCertificate() {
    CertificateDto certificateDto = new CertificateDto();
    certificateDto.setName("testCertificate");
    return certificateService.saveCertificate(certificateDto);
  }

  @Test
  void certificateUpdateTest() {
    CertificateDto certificateDtoToUpdate = new CertificateDto();

    certificateDtoToUpdate.setName("updated");

    Certificate initCertificate = getSavedCertificate();

    certificateService.updateCertificate(certificateDtoToUpdate, initCertificate.getUuid());

    Certificate updatedCertificate =
        certificateService.findByUuid(initCertificate.getUuid());

    Assertions.assertEquals(certificateDtoToUpdate.getName(),
        updatedCertificate.getName());
  }
}