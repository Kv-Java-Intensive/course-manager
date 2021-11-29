package com.itacademy.cms.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private Date startDate;

    @NotNull
    private int capacity;

    @NotNull
    private LocalTime startLesson;
}
