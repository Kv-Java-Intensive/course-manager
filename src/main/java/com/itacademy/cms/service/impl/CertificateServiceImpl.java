package com.itacademy.cms.service.impl;

//import com.itacademy.cms.jparepository.CertificateRepository;
//import com.itacademy.cms.mapper.CertificateMapper;

import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.Certificate;
import com.itacademy.cms.model.dto.CertificateDto;
import com.itacademy.cms.repository.CertificateDAO;
import com.itacademy.cms.service.CertificateService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {
  //@Autowired
  private final CertificateDAO certificatesRepository;

  private final MapStructMapper certificateMapper;


  @Override
  public List<Certificate> findAll() {
    return (List<Certificate>) certificatesRepository.findAll();
  }

  @Override
  public void saveCertificate(CertificateDto certificateDto) {
    certificatesRepository.save(
        certificateMapper.certificateDtoToCertificate(certificateDto));
  }

  @Override
  public Certificate findById(long id) {
    Optional<Certificate> certificate = certificatesRepository.findById(id);
    return certificatesRepository.getById(id);
  }

  @Override
  public void deleteCertificate(long id) {
    certificatesRepository.deleteById(id);
  }
}

