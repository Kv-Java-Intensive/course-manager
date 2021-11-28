package com.itacademy.cms.model;

import javax.persistence.*;

@Entity
@Table(name="course_tags")
public class CourseTags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "course")
    private int course;

    @Column(name = "tags")
    private int tags;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tags")
    private Tags tagsCourse;

    public CourseTags() {
    }

    public CourseTags(int course, int tags) {
        this.course = course;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getTags() {
        return tags;
    }

    public void setTags(int tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "CourseTags{" +
                "id=" + id +
                ", course=" + course +
                ", tags=" + tags +
                '}';
    }
}
