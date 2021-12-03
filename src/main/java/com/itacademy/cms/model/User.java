package com.itacademy.cms.model;

import com.itacademy.cms.model.enums.Role;
import com.sun.istack.NotNull;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
public class User extends BaseEntity {

  @OneToMany(orphanRemoval = true)
  @JoinColumn(name = "user_id")
  private List<UserToCourse> userCourse;

  @ManyToMany(cascade = CascadeType.ALL)
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

