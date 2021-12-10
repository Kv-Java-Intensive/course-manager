package com.itacademy.cms.service.impl;

import com.itacademy.cms.exeption.EntityNotFoundException;
import com.itacademy.cms.exeption.ParameterMissingException;
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
    List<Certificate> certificateList = certificatesRepository.findAll();
    if (certificateList.isEmpty()) {
      throw new EntityNotFoundException("No certificates found!");
    }
    return certificateList;
  }

  @Override
  public Certificate saveCertificate(CertificateDto certificateDto) {
    return certificatesRepository.save(
        certificateMapper.certificateDtoToCertificate(certificateDto));
  }

  @Override
  public Certificate findByUuid(String uuid) {
    Optional<Certificate> certificate = certificatesRepository.findByUuid(uuid);
    return certificate.orElseThrow(
        () -> new EntityNotFoundException("Certificate with uuid " + uuid + " not found!"));
  }

  @Override
  public void updateCertificate(CertificateDto certificateDto, String uuid) {
    Optional<Certificate> certificateOptional = certificatesRepository.findByUuid(uuid);
    certificateOptional.ifPresent(certificate -> {
          certificate.setName(certificateDto.getName());
          certificatesRepository.save(certificate);
        }
    );
  }

  @Override
  public void deleteCertificateByUuid(String uuid) {
    if (uuid == null) {
      throw new ParameterMissingException("Certificate uuid is missing");
    } else if (certificatesRepository.existsByUuid(uuid)) {
      certificatesRepository.deleteByUuid(uuid);
      return;
    }
    throw new EntityNotFoundException("Certificate with uuid " + uuid + " not found!");
  }
}