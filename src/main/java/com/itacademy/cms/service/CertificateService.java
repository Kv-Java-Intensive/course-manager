package com.itacademy.cms.service;

import com.itacademy.cms.model.Certificate;
import com.itacademy.cms.model.dto.CertificateDto;
import java.util.List;

public interface CertificateService {

  List<Certificate> findAll();

  void updateCertificate(CertificateDto certificateDto, String uuid);

  Certificate saveCertificate(CertificateDto certificateDto);

  Certificate findByUuid(String uuid);

  void deleteCertificateByUuid(String uuid);
}