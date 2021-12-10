package com.itacademy.cms.model;

import com.itacademy.cms.model.enums.CourseStatus;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

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

  @EmbeddedId
  protected UserToCourseId id = new UserToCourseId();
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

  }

  @Embeddable
  public static class UserToCourseId implements Serializable {

    @Column(name = "user_id")
    protected Long userId;

    @Column(name = "course_id")
    protected Long courseId;

    public UserToCourseId() {
    }

    public UserToCourseId(Long userId, Long courseId) {
      this.userId = userId;
      this.courseId = courseId;
    }
  }
}