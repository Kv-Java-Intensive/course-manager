package com.itacademy.cms.service;

import com.itacademy.cms.exeption.CertificateNotFoundException;
import com.itacademy.cms.model.Certificate;
import com.itacademy.cms.model.dto.CertificateDto;
import java.util.List;

public interface CertificateService {
  List<Certificate> findAll();

  Certificate findById(Long id) throws CertificateNotFoundException;

  void updateCertificate(CertificateDto certificateDto, Long id);

  Certificate saveCertificate(CertificateDto certificateDto);

  void deleteCertificateById(Long id) throws CertificateNotFoundException;

}