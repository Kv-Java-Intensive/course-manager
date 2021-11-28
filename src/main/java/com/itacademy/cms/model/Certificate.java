package com.itacademy.cms.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "certificates")
@Data
public class Certificate {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer course_id;

    @OneToOne(mappedBy = "certificate")
    private UserCertificates userCertificates;

}
