package com.itacademy.cms.controller;

import com.itacademy.cms.model.Certificate;
import com.itacademy.cms.service.CertificateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CertificateController {

    private CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping("/certificates")
    public List<Certificate> showAllCertificates() {
        List<Certificate> allCertificates = certificateService.getAllCertificates();
        return allCertificates;
    }

    @GetMapping("/certificates/{id}")
    public Optional<Certificate> getCertificate(@PathVariable long id) {
        Optional<Certificate> certificate = certificateService.findCertificateById(id);
        return certificate;
    }

    @PostMapping("/certificates")
    public Certificate addNewCertificate(@RequestBody Certificate certificate) {
        certificateService.saveCertificate(certificate);
        return certificate;
    }

    @PutMapping("/employees")
    public Certificate updateCertificate(@RequestBody Certificate certificate) {
        certificateService.saveCertificate(certificate);
        return certificate;
    }

    @DeleteMapping("certificates/{id}")
    public String deleteCertificate(@PathVariable long id) {
        certificateService.findCertificateById(id);
        certificateService.deleteCertificate(id);
        return "Certificate with Id " + id + " was deleted";
    }
}
