package com.itacademy.cms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "courses")
public class Course extends BaseEntity{
//  @Id
//  @CreatedDate
//  //@Date
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private Long id;
  @Column(name = "course_name")
  private String courseName;
  @Column(name = "description")
  private String description;
  @Column(name = "price")
  private Double price;
  @ManyToOne
  @JoinColumn(name = "course_category")
  private Category category;//(fk to disipline)
  @Column(name = "update_date")
  @Temporal(TemporalType.DATE)
  private Date updateDate;
  @Column(name = "duration")
  @Temporal(TemporalType.TIME)
  private Date duration; //hours
  @Column(name = "language")
  private Language language;
  @OneToMany(orphanRemoval = true)
  @JoinColumn(name = "courses_id")
  private List<UserToCourse> userCourses;
  @ManyToMany(mappedBy = "tags")
//  @OneToMany(orphanRemoval = true)
//  @JoinColumn(name = "courses_id")
  private List<Tag> courseTags;
  @OneToMany(mappedBy = "courses")
  private List<Group> groups;

}
