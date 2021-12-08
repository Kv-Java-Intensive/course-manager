package com.itacademy.cms.repository;

import com.itacademy.cms.model.Certificate;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface CertificateRepository extends CrudRepository<Certificate, UUID> {

  Certificate getById(UUID id);
}