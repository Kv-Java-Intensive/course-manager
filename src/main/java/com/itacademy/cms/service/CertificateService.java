package com.itacademy.cms.service;

import com.itacademy.cms.model.Certificate;
import com.itacademy.cms.model.dto.CertificateDto;
import com.itacademy.cms.model.dto.SearchCriteriaDto;
import java.util.List;

public interface CertificateService {

  List<Certificate> findAll();

  void updateCertificate(CertificateDto certificateDto, String uuid);
  
  List<Certificate> findCertificateBySearch(SearchCriteriaDto searchCriteriaDto);

  Certificate saveCertificate(CertificateDto certificateDto);

  Certificate findByUuid(String uuid);

  void deleteCertificateByUuid(String uuid);
}
