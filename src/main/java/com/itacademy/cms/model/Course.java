package com.itacademy.cms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.Tag;
import com.itacademy.cms.model.enums.Language;
import com.itacademy.cms.model.UserToCourse;
import com.itacademy.cms.model.Group;
import com.itacademy.cms.model.Module;
import com.itacademy.cms.model.Certificate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "courses")
public class Course extends BaseEntity{

  @Column(name = "course_name")
  @NotNull
  private String courseName;

  @Column(name = "description")
  @NotNull
  private String description;

  @NotNull
  @Column(name = "price")
  private Double price;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "course_category")
  private Category category;

  @NotNull
  @Column(name = "update_date")
  @Temporal(TemporalType.DATE)
  private Date updateDate;

  @NotNull
  @Column(name = "duration")
  @Temporal(TemporalType.TIME)
  private Date duration; //hours

  @NotNull
  @Column(name = "language")
  private Language language;

  @OneToMany(orphanRemoval = true)
  @JoinColumn(name = "course_id")
  private List<UserToCourse> userCourses;

  @ManyToMany(cascade = CascadeType.ALL, mappedBy = "course")
  private List<Tag> courseTags;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
  private List<Group> groups;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
  private List<Module> modules;

  @NotNull
  @OneToOne(cascade = CascadeType.ALL, mappedBy = "course")
  private Certificate certificate;
}
