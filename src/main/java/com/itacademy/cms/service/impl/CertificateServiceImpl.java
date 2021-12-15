package com.itacademy.cms.service.impl;

import com.itacademy.cms.exeption.EntityNotFoundException;
import com.itacademy.cms.exeption.ParameterMissingException;
import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.Certificate;
import com.itacademy.cms.model.dto.CertificateDto;
import com.itacademy.cms.model.dto.SearchCriteriaDto;
import com.itacademy.cms.repository.CertificateRepository;
import com.itacademy.cms.repository.specification.CertificateSpecificationsBuilder;
import com.itacademy.cms.service.CertificateService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CertificateServiceImpl implements CertificateService {

  private static final Logger logger = LoggerFactory.getLogger(CertificateServiceImpl.class);

  private final CertificateRepository certificatesRepository;
  private final MapStructMapper certificateMapper;

  @Override
  public List<Certificate> findAll() {
    log.info("GET ALL CERTIFICATES");
    List<Certificate> certificateList = certificatesRepository.findAll();
    if (certificateList.isEmpty()) {
      logger.error("NO CATEGORIES CERTIFICATES");
      throw new EntityNotFoundException("No certificates found!");
    }
    return certificateList;
  }

  public List<Certificate> findCertificateBySearch(SearchCriteriaDto searchCriteriaDto) {
    CertificateSpecificationsBuilder builder = new CertificateSpecificationsBuilder();
    for (int i = 0; i < searchCriteriaDto.getCriteriaList().size(); i++) {
      builder.with(searchCriteriaDto.getCriteriaList().get(i).getKey(),
          searchCriteriaDto.getCriteriaList().get(i).getOperation(),
          searchCriteriaDto.getCriteriaList().get(i).getValue());
    }
    Specification<Certificate> spec = builder.build();
    return certificatesRepository.findAll(spec);
  }

  @Override
  public Certificate saveCertificate(CertificateDto certificateDto) {
    logger.info("CREATE NEW CERTIFICATES");
    return certificatesRepository.save(
        certificateMapper.certificateDtoToCertificate(certificateDto));
  }

  @Override
  public Certificate findByUuid(String uuid) {
    logger.info("GET CERTIFICATE WITH ID = {}", uuid);
    Optional<Certificate> certificate = certificatesRepository.findByUuid(uuid);
    if (certificate.isPresent()) {
      return certificate.get();
    } else {
      logger.error("CERTIFICATE WITH ID = {} DOES NOT EXIST", uuid);
      throw new EntityNotFoundException("Certificate with uuid " + uuid + " not found!");
    }
  }

  @Override
  public void updateCertificate(CertificateDto certificateDto, String uuid) {
    logger.info("UPDATE CERTIFICATE WITH ID = {}", uuid);
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
      logger.error("UUID IS EMPTY");
      throw new ParameterMissingException("Certificate uuid is missing");
    } else if (certificatesRepository.existsByUuid(uuid)) {
      logger.info("REMOVE CERTIFICATE WITH ID = {}", uuid);
      certificatesRepository.deleteByUuid(uuid);
      return;
    }
    logger.error("CERTIFICATE WITH ID = {} DOES NOT EXIST", uuid);
    throw new EntityNotFoundException("Certificate with uuid " + uuid + " not found!");
  }
}
