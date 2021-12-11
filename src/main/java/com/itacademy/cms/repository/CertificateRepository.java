package com.itacademy.cms.repository;

import com.itacademy.cms.model.Certificate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
  Optional<Certificate> findByUuid(String uuid);

  boolean existsByUuid(String uuid);

  void deleteByUuid(String uuid);
}