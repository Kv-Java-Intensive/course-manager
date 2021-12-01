package com.itacademy.cms.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tags")
public class Tag extends BaseEntity{


    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "courses"
            , joinColumns = @JoinColumn(name = "tag_id")
            , inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;
}
