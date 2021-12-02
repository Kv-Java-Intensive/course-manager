package com.itacademy.cms.model.dto;

import com.itacademy.cms.model.Course;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CategoryDTO {

    private List<Course> courses;
    private String category;
}
