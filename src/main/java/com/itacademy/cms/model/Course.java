package com.itacademy.cms.model;

import com.itacademy.cms.model.enums.Language;
import com.sun.istack.NotNull;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
  //  @Id
//  Long id;
  @Column(name = "course_name")
  @NotNull
  private String courseName;

  @Column(name = "description")
  @NotNull
  private String description;

  //@NotNull
  @Column(name = "price")
  private Double price;

  //@NotNull
  @ManyToOne
  @JoinColumn(name = "course")
  //@JoinTable(name = "course_category")
  private Category category;

  @NotNull
  @Column(name = "update_date")
  @Temporal(TemporalType.DATE)
  private Date updateDate;

  @NotNull
  @Column(name = "duration")
  private Double duration;

  @NotNull
  @Column(name = "language")
  private Language language;

  @OneToMany(mappedBy = "course", cascade = {CascadeType.MERGE, CascadeType.DETACH,
      CascadeType.PERSIST, CascadeType.REFRESH})
  private List<UserToCourse> userCourses;


  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "course_tags", joinColumns = @JoinColumn(name = "course_id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id"))
  private List<Tag> courseTags;

  @OneToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH,
      CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "course")
  private List<Group> groups;

  @OneToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH,
      CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "course")
  private List<Module> modules;

  //@NotNull
  @OneToOne(cascade = CascadeType.ALL, mappedBy = "course")
  // @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "course")
  private Certificate certificate;
}
