package com.itacademy.cms.service;

import com.itacademy.cms.model.Certificate;
import java.util.List;
import java.util.Optional;

public interface CertificateService {
  List<Certificate> getAllCertificates();

  void saveCertificate(Certificate certificate);

  Optional<Certificate> findCertificateById(long id);

  void deleteCertificate(long id);
}