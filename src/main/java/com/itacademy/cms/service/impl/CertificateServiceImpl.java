package com.itacademy.cms.service.impl;

import com.itacademy.cms.exeption.CertificateNotFoundException;
import com.itacademy.cms.exeption.ParameterMissingException;
import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.Certificate;
import com.itacademy.cms.model.dto.CertificateDto;
import com.itacademy.cms.repository.CertificateRepository;
import com.itacademy.cms.service.CertificateService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

  private final CertificateRepository certificatesRepository;
  private final MapStructMapper certificateMapper;


  @Override
  public List<Certificate> findAll() {
    List<Certificate> certificateList = certificatesRepository.findAll();
    if (certificateList.isEmpty()) {
      throw new CertificateNotFoundException("No certificates found!");
    }
    return certificateList;
  }

  @Override
  public Certificate saveCertificate(CertificateDto certificateDto) {
    return certificatesRepository.save(
        certificateMapper.certificateDtoToCertificate(certificateDto));
  }

  @Override
  public Certificate findById(UUID id) {
    Optional<Certificate> certificate = certificatesRepository.findById(id);
    return certificate.orElseThrow(
        () -> new CertificateNotFoundException("Certificate with id " + id + " not found!"));
  }

  @Override

  public void updateCertificate(CertificateDto certificateDto, Long id) {

    Optional<Certificate> certificateOptional = certificatesRepository.findById(id);
    certificateOptional.ifPresent(certificate -> {
          certificate.setName(certificateDto.getName());
          certificatesRepository.save(certificate);
        }
    );
  }

  @Override

  public void deleteCertificateById(Long id) {

    if (id == null) {
      throw new ParameterMissingException("Certificate id is missing");
    } else if (certificatesRepository.existsById(id)) {
      certificatesRepository.deleteById(id);
      return;
    }
    throw new CertificateNotFoundException("Certificate with id " + id + " not found!");
  }
}