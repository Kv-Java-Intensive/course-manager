package com.itacademy.cms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "courses")
public class Courses {
  @Id
  @GeneratedValue
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


}