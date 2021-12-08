package com.itacademy.cms.service;

import com.itacademy.cms.model.Certificate;
import com.itacademy.cms.model.dto.CertificateDto;
import java.util.List;
import java.util.UUID;

public interface CertificateService {


  List<Certificate> findAll();

  Certificate findById(UUID id);

  // void updateCertificate(CertificateDto certificateDto, UUID id);

  void saveCertificate(CertificateDto certificateDto);

  void deleteCertificateById(UUID id);
}

