package com.itacademy.cms.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "groups")
public class Group extends BaseEntity {

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
<<<<<<< HEAD
      name = "users",
=======
      name = "users_groups",
>>>>>>> main
      joinColumns = {@JoinColumn(name = "group_id")},
      inverseJoinColumns = {@JoinColumn(name = "user_id")}
  )
  private List<User> users;

  @NotNull
  private String name;

  @Column(name = "start_date")
  private LocalDateTime startDate;

  @NotNull
  private int capacity;

  @ManyToOne
  @JoinColumn(name = "course_id", nullable = false)
  private Course course;
}
