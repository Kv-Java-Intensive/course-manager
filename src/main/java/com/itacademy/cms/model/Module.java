package com.itacademy.cms.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "modules")
public class Module {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    @Getter
    @Setter
    private Course course;

    @Column(name = "lesson_num")
    @Getter
    @Setter
    private int lesson_num;

    @Column(name = "description")
    @Getter
    @Setter
    private String description;

    @Column(name = "content")
    @Getter
    @Setter
    private String content;

    public Module() {
    }

    public Module(int lesson_num, String description, String content) {
        this.lesson_num = lesson_num;
        this.description = description;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", lesson_num=" + lesson_num +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
