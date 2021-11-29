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
public class Module {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

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
