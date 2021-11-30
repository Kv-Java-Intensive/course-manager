package com.itacademy.cms.model;

import com.itacademy.cms.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User extends BaseEntity {

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<UserToCourse> userCourse;

    @ManyToMany(mappedBy = "users")
    private List<Group> groups;

    @NotNull
    @Column(name = "first_name")
    private String firstName;
    @NotNull
    @Column(name = "last_name")
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String password;

    @Column(name = "account_card")
    private double accountCard;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;
    private String about;
}