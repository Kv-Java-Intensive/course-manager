package com.itacademy.cms.repository;

import com.itacademy.cms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<User, Long> {
}
