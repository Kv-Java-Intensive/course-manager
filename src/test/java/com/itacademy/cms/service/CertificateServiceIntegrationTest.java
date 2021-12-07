package com.itacademy.cms.service;

import com.itacademy.cms.model.Certificate;
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
  UserRepository certificateRepository;

  @BeforeEach
  void cleanTable() {
    certificateRepository.deleteAll();
  }

  @Test
  void certificateSaveAndFindByIdTest() {
    Certificate savedCertificate = getSavedCertificate();

    Certificate certificateById = certificateService.findById(savedCertificate.getId());

    Assertions.assertEquals(savedCertificate.getName(), certificateById.getName());
  }

  @Test
  void certificateDeleteTest() {
    Certificate initCertificate = getSavedCertificate();

    Assertions.assertNotNull(
        certificateService.findById(initCertificate.getId()));

    certificateService.deleteCertificateById(initCertificate.getId());

    Assertions.assertThrows(CertificateNotFoundException.class,
        () -> certificateService.findById(initCertificate.getId()));
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

    certificateService.updateCertificate(certificateDtoToUpdate, initCertificate.getId());

    Certificate updatedCertificate =
        certificateService.findById(initCertificate.getId());

    Assertions.assertEquals(certificateDtoToUpdate.getName(),
        updatedCertificate.getName());
  }
}