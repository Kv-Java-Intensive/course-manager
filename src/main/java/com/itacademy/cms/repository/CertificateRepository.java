package com.itacademy.cms.repository;

import com.itacademy.cms.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CertificateRepository extends CrudRepository<Certificate, Long> {

  Certificate getById(long id);
}