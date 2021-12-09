package com.itacademy.cms.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
  //  @Id
//  Long id;
  // @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
  @OneToMany(cascade = CascadeType.ALL)//, mappedBy = "category")
  @JoinColumn(name = "course")
  private List<Course> courses;
  private String categoryName;
}
