package com.itacademy.cms.controller;

import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.dto.CertificateDto;
import com.itacademy.cms.service.CertificateService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CertificateController {

  private final CertificateService certificateService;
  private final MapStructMapper certificateMapper;


  @GetMapping("/certificates")
  public List<CertificateDto> getAllCertificates() {
    return certificateService.findAll().stream()
        .map(certificateMapper::certificateToCertificateDto).collect(Collectors.toList());
  }

  @GetMapping("/certificates/{id}")
  public CertificateDto getCertificateById(@PathVariable("id") UUID id) {
    return certificateMapper.certificateToCertificateDto(certificateService.findById(id));
  }

  @PostMapping("/certificates")
  public CertificateDto saveCertificate(@RequestBody CertificateDto certificateDto) {
    certificateService.saveCertificate(certificateDto);
    return certificateDto;
  }

  @PutMapping("/employees/{id}")
  public void updateCertificate(@RequestBody CertificateDto certificateDto, @PathVariable UUID id) {
    certificateService.updateCertificate(certificateDto, id);
  }

  @DeleteMapping("certificates/{id}")
  public void deleteCertificate(@PathVariable("id") UUID id) {
    certificateService.findById(id);
    certificateService.deleteCertificateById(id);
  }
}