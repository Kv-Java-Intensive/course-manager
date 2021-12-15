package com.itacademy.cms.model;

import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category extends BaseEntity {

  @OneToMany(cascade = CascadeType.ALL)//, mappedBy = "category")
  @JoinColumn(name = "course_category")
  private List<Course> courses;

  @Column(name = "category_name")
  private String categoryName;

  @Column(name = "uuid")
  private String uuid;

  @PrePersist
  public void autofill() {
    this.setUuid(UUID.randomUUID().toString());
  }
}
