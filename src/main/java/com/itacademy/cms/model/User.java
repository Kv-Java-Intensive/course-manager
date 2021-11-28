package com.itacademy.cms.model;

import com.itacademy.cms.model.enums.Role;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private double accountCard;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String about;
}