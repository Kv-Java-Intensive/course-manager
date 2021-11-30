package com.itacademy.cms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "groups")
public class Group extends BaseEntity{

    @ManyToMany
    @JoinTable(
            name = "users",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> users;

    @NotNull
    private String name;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @NotNull
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}
