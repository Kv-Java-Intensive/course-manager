package com.itacademy.cms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category extends BaseEntity{

    @OneToMany (mappedBy="category_id", fetch=FetchType.LAZY)
    private List<Course> courses;
    private String category;
}
