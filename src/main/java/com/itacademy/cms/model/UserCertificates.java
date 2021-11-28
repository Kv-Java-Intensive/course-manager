package com.itacademy.cms.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "userCertificates")
@Data
public class UserCertificates {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "certificate")
    @OneToOne
    @JoinColumn(referencedColumnName = "")
    private Certificate certificate;

    @Column
    private int user_id;

}
