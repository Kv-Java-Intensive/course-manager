package com.itacademy.cms.service;

import com.itacademy.cms.model.Certificate;
import com.itacademy.cms.model.dto.CertificateDto;
import com.itacademy.cms.model.dto.SearchCriteriaDto;
import java.util.List;

public interface CertificateService {

  List<Certificate> findAll();

  List<Certificate> findCertificateBySearch(SearchCriteriaDto searchCriteriaDto);

  Certificate findById(Long id);

  void updateCertificate(CertificateDto certificateDto, Long id);

  Certificate saveCertificate(CertificateDto certificateDto);

  void deleteCertificateById(Long id);
}