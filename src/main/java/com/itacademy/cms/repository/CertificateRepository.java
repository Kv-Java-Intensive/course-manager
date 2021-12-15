package com.itacademy.cms.repository;

import com.itacademy.cms.model.Certificate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository
    extends JpaRepository<Certificate, Long>, JpaSpecificationExecutor<Certificate> {
  Optional<Certificate> findByUuid(String uuid);

  boolean existsByUuid(String uuid);

  void deleteByUuid(String uuid);
}
