package com.itacademy.cms.model;

import com.itacademy.cms.model.enums.Role;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn
  List<Certificate> certificates;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToMany(mappedBy = "user", orphanRemoval = true)
  private List<UserToCourse> userCourse;
  @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)

  private List<Group> groups;
  @NotNull
  @NotBlank(message = "There should be a name")
  @Size(min = 2, max = 12, message = "Name should be more between 2 and 15 characters length")
  @Column(name = "first_name")
  private String firstName;
  @NotNull
  @NotBlank(message = "There should be a last name")
  @Size(min = 2, max = 12, message = "Last name should be more between 2 and 15 characters length")
  @Column(name = "last_name")
  private String lastName;
  @NotNull
  @Email(message = "Email should be of valid form")
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

