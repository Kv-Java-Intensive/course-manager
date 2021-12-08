package com.itacademy.cms.service;

import com.itacademy.cms.model.Certificate;
import com.itacademy.cms.model.dto.CertificateDto;
import java.util.List;
import java.util.UUID;

public interface CertificateService {

  List<Certificate> findAll();

  Certificate findById(Long id);

  void updateCertificate(CertificateDto certificateDto, Long id);

  Certificate saveCertificate(CertificateDto certificateDto);

  void deleteCertificateById(Long id);

}