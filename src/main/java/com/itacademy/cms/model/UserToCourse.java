package com.itacademy.cms.model;

import lombok.*;

import javax.persistence.*;
import com.itacademy.cms.model.User;
import com.itacademy.cms.model.Course;
import org.hibernate.annotations.Immutable;
import com.itacademy.cms.model.enums.CourseStatus;

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

  @ManyToOne
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "course_id", insertable = false, updatable = false)
  private Course course;

  @NonNull
  @Column(name = "course_status")
  private CourseStatus courseStatus;

  @NonNull
  @Column(name = "is_author")
  private boolean isAuthor;

  public UserToCourse(User user, Course course, CourseStatus courseStatus, boolean isAuthor) {
    this.user = user;
    this.course = course;
    this.courseStatus = courseStatus;
    this.isAuthor = isAuthor;
    this.id.userId = user.getId();
    this.id.courseId = course.getId();
    user.getUserToCourse().add(this);
    course.getUserToCourse().add(this);
  }
}