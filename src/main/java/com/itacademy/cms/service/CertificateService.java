package com.itacademy.cms.service;

import com.itacademy.cms.model.Certificate;
import com.itacademy.cms.model.dto.CertificateDto;
import java.util.List;
import java.util.Optional;

public interface CertificateService {
  List<Certificate> findAll();

  void saveCertificate(CertificateDto certificateDto);

  Certificate findById(long id);

  void deleteCertificate(long id);
}