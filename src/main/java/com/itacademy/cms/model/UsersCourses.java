package com.itacademy.cms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;

import javax.persistence.*;
import com.itacademy.cms.model.Users;
import com.itacademy.cms.model.Course_status;
import com.itacademy.cms.model.Groups;
import com.itacademy.cms.model.Courses;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users_couses")
public class UsersCourses {
  @EmbeddedId
  @JsonUnwrapped
  private UsersCoursesCompositeKey usersCoursesCompositeKey;
  @ManyToOne
  @JoinColumn(name = "users_courses_groups")
  private Groups group; //fk to groups
  @JsonIgnore
  @MapsId("usersId")
  @ManyToOne
  private Users user; //fk to users
  @JsonIgnore
  @MapsId("coursesId")
  @ManyToOne
  private Courses course; //fk to course
  @ManyToOne
  @JoinColumn(name = "users_courses_course_status")
  private Course_status course_status; //fk to course_status
}
