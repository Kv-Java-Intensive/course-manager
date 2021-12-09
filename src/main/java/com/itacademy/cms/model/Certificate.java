package com.itacademy.cms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "certificates")
@NoArgsConstructor
@Getter
@Setter
public class Certificate extends BaseEntity {


  @Column
  @NotNull(message = "Certificate name should be present")
  @Size(min = 10, max = 30, message =
      "Certificate name length should be in scope between 10 and 30 letters")
  private String name;


  @OneToOne
  private Course course;


  @ManyToOne
  private User user;
}