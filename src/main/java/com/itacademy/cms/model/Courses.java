package com.itacademy.cms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import com.itacademy.cms.model.Discipline;
import com.itacademy.cms.model.Language;
import com.itacademy.cms.model.CourseTags;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Courses {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "course_name")
  private String courseName;
  @Column(name = "description")
  private String description;
  @Column(name = "price")
  private Double price;
  @ManyToOne
  @JoinColumn(name = "course_discipline")
  private Discipline type;//(fk to disipline)
  @Column(name = "update_date")
  @Temporal(TemporalType.DATE)
  private Date updateDate;
  @Column(name = "sertifiable")
  private boolean isSertifiable;
  @Column(name = "duration")
  private Double duration; //hours
  @ManyToOne
  @JoinColumn(name = "course_language")
  private Language language;//(fk to language)
  @JsonIgnore
  @OneToMany(orphanRemoval = true)
  @JoinColumn(name = "courses_id")
  private List<UsersCourses> userCourses;
  @JsonIgnore
  @OneToMany(orphanRemoval = true)
  @JoinColumn(name = "courses_id")
  private List<CourseTags> courseTags;

}
