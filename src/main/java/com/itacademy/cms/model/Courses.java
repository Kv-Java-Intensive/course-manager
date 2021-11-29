package com.itacademy.cms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.Category;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.CourseTags;
import org.springframework.data.annotation.CreatedDate;

//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "courses")
public class Courses {
  @Id
  @CreatedDate
  //@Date
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
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
  private Double duration; //hours
  @Column(name = "language")
//  private Enum language;//(fk to language)  ///??
  @JsonIgnore
  @OneToMany(orphanRemoval = true)
  @JoinColumn(name = "courses_id")
  private List<UserToCourse> userCourses;
  //@JsonIgnore
  @OneToMany(orphanRemoval = true)
  @JoinColumn(name = "courses_id")
  private List<CourseTags> courseTags;  //???
  //certificate one to one

}
