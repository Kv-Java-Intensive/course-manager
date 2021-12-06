package com.itacademy.cms.service;

import com.itacademy.cms.exeption.CertificateNotFoundException;
import com.itacademy.cms.model.Certificate;
import com.itacademy.cms.model.dto.CertificateDto;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class CertificateServiceIntegrationTest {

  @Autowired
  CertificateService certificateService;

  @Autowired
  EntityManager entityManager;

  @Test
  void certificateSaveAndFindByIdTest() throws CertificateNotFoundException {
    Certificate savedCertificate = getSavedCertificate();

    Certificate certificateById = certificateService.findById(savedCertificate.getId());

    Assertions.assertEquals(savedCertificate.getName(), certificateById.getName());
  }

  @Test
  void certificateDeleteTest() throws CertificateNotFoundException {
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

//  @Test
//  void certificateUpdateTest() throws CertificateNotFoundException {
//    CertificateDto certificateDtoToUpdate = new CertificateDto();
//
//    certificateDtoToUpdate.setName("updated");
//
//    Certificate initCertificate = getSavedCertificate();
//
//    certificateService.updateCertificate(
//        Certificate(certificateDtoToUpdate, initCertificate.getId());
//
//    entityManager.flush();
//    entityManager.clear();
//
//    Certificate updatedCertificate =
//        certificateService.findById(initCertificate.getId());
//
//    Assertions.assertEquals(certificateDtoToUpdate.getName(),
//        updatedCertificate.getName());
//  }
}