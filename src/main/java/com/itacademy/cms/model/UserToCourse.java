package com.itacademy.cms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;

import javax.persistence.*;
import com.itacademy.cms.model.User;
import com.itacademy.cms.model.Course_status;
import com.itacademy.cms.model.Group;
import com.itacademy.cms.model.Course;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "user_course")
@Immutable
public class UserToCourse {
  @Embeddable
  public static class Id implements Serializable {

    @Column(name = "user_id")
    protected Long userId;

    @Column(name = "course_id")
    protected Long courseId;

    public Id() {
    }

    public Id(Long userId, Long courseId) {
      this.userId = userId;
      this.courseId = courseId;
    }
  }

  @EmbeddedId
  protected Id id = new Id();

  // private UsersCoursesCompositeKey usersCoursesCompositeKey;
//  @ManyToOne
//  @JoinColumn(name = "users_courses_groups")
//  private Group group; //fk to groups

  @ManyToOne
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private User user; //fk to users
  @ManyToOne
  @JoinColumn(name = "course_id", insertable = false, updatable = false)
  private Course course; //fk to course

  //  @ManyToOne ///Enum status course
//  @JoinColumn(name = "users_courses_course_status")
//  private Course_status course_status; //fk to course_status
  //роль автор курса?
  public UserToCourse(User user, Course course) {
    this.user = user;
    this.course = course;
    this.id.userId = user.getId();
    this.id.courseId = course.getId();
    user.getUserToCourse().add(this);
    course.getUserToCourse().add(this);
  }
}