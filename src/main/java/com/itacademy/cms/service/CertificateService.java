package com.itacademy.cms.service;

import com.itacademy.cms.model.Certificate;

import java.util.List;
import java.util.Optional;

public interface CertificateService {
    public List<Certificate> getAllCertificates();

    public void saveCertificate(Certificate certificate);

    public Optional<Certificate> findCertificateById(long id);

    public void deleteCertificate(long id);
}