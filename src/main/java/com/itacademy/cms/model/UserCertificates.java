package com.itacademy.cms.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "userCertificates")
@NoArgsConstructor
public class UserCertificates {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    @Setter
    private Long id;

    @Column(name = "certificate")
    @OneToOne
    @JoinColumn(referencedColumnName = "")
    @Getter
    @Setter
    private Certificate certificate;

    @Column
    @Getter
    @Setter
    private int user_id;

}
