package com.itacademy.cms.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "languages")
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany (mappedBy="language_id", fetch=FetchType.LAZY)
    private List<Courses> courses;
    private String language;


}