package com.itacademy.cms.model;

import javax.persistence.*;

@Entity
@Table(name = "tags")
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "tagsCourse",
            cascade ={ CascadeType.PERSIST, CascadeType.REFRESH})
    private CourseTags courseTags;

    public Tags() {
    }

    public Tags(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CourseTags getCourseTags() {
        return courseTags;
    }

    public void setCourseTags(CourseTags courseTags) {
        this.courseTags = courseTags;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}