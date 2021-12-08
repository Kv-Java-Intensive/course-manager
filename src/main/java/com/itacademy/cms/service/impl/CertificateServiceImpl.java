package com.itacademy.cms.service.impl;

import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.Certificate;
import com.itacademy.cms.model.dto.CertificateDto;
import com.itacademy.cms.repository.CertificateRepository;
import com.itacademy.cms.service.CertificateService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {
  private final CertificateRepository certificatesRepository;

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
  public Certificate findById(Long id) {
    Optional<Certificate> certificate = certificatesRepository.findById(id);
    return certificatesRepository.getById(id);
  }

  @Override
  public void deleteCertificateById(Long id) {
    certificatesRepository.deleteById(id);
  }
}

