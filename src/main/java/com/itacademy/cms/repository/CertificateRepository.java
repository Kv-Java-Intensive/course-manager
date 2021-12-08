package com.itacademy.cms.repository;

import com.itacademy.cms.model.Certificate;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, UUID> {
}