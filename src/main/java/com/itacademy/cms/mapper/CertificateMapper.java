package com.itacademy.cms.mapper;

import com.itacademy.cms.model.Certificate;
import com.itacademy.cms.model.dto.CertificateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CertificateMapper {
  CertificateMapper CERTIFICATE_MAPPER =
      Mappers.getMapper(CertificateMapper.class);

  Certificate certificateDtoToCertificate(CertificateDto certificateDto);

  CertificateDto certificateToCertificateDto(Certificate Certificate);
}