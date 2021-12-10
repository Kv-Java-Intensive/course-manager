package com.itacademy.cms.model;


import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

  //  @ManyToMany(fetch = FetchType.LAZY,
//      mappedBy = "tags")
  @ManyToMany(cascade = CascadeType.ALL)//, mappedBy = "tags")
  @JoinTable(
      name = "course_tags",
      joinColumns = @JoinColumn(name = "tag_id"),
      inverseJoinColumns = @JoinColumn(name = "course_id"))
  private List<Course> courses;
}
