package com.itacademy.cms.model;


import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tags")
public class Tag extends BaseEntity {

  //  @Id
//  Long id;
  @NotNull
  @Column(name = "name")
  private String name;

  @ManyToMany(cascade = CascadeType.ALL)//, mappedBy = "tags")
  @JoinTable(
      name = "course_tags",
      joinColumns = @JoinColumn(name = "tag_id"),
      inverseJoinColumns = @JoinColumn(name = "course_id"))
  private List<Course> courses;

  @Column(name = "uuid")
  private String uuid;

  @PrePersist
  public void autofill() {
    this.setUuid(UUID.randomUUID().toString());
  }
}
