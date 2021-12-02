package com.itacademy.cms.service.impl;

import com.itacademy.cms.jpaRepository.CertificateRepository;
import com.itacademy.cms.model.Certificate;
import com.itacademy.cms.service.CertificateService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertificateServiceImpl implements CertificateService {

    private CertificateRepository certificatesRepository;

    public CertificateServiceImpl(CertificateRepository certificatesRepository) {
        this.certificatesRepository = certificatesRepository;
    }

    @Override
    public List<Certificate> getAllCertificates() {
        return certificatesRepository.findAll();
    }

    @Override
    public void saveCertificate(Certificate certificate) {
        certificatesRepository.save(certificate);
    }

    @Override
    public  Optional<Certificate> findCertificateById(long id) {
        return certificatesRepository.findById(id);
    }

    @Override
    public void deleteCertificate(long id) {
        certificatesRepository.deleteById(id);
    }
}