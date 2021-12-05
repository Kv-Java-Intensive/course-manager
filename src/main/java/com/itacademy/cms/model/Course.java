package com.itacademy.cms.model;

import com.itacademy.cms.model.enums.Language;
import com.sun.istack.NotNull;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "courses")
public class Course extends BaseEntity {

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
  @ManyToOne//(cascade = CascadeType.ALL, mappedBy = "category")
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

  @ManyToMany(cascade = CascadeType.ALL) //, mappedBy = "course")
  private List<Tag> courseTags;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
  private List<Group> groups;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
  private List<Module> modules;

  @NotNull
  @OneToOne(cascade = CascadeType.ALL, mappedBy = "course")
  private Certificate certificate;
}
