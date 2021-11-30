package com.itacademy.cms.model;

import lombok.*;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "modules")
public class Module extends BaseEntity{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "lesson_num")
    private int lessonNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content;
}
