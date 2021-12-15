package com.itacademy.cms.repository.specification;

import com.itacademy.cms.model.Course;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;

public class CourseSpecificationsBuilder {
  private final List<SearchCriteria> criterias;

  public CourseSpecificationsBuilder() {
    criterias = new ArrayList<>();
  }

  public CourseSpecificationsBuilder with(String key, String operation, String value) {
    criterias.add(new SearchCriteria(key, operation, value));
    return this;
  }

  public Specification<Course> build() {
    if (criterias.size() == 0) {
      return null;
    }

    List<Specification<Course>> specs = criterias.stream()
        .map(CourseSpecification::new)
        .collect(Collectors.toList());

    Specification<Course> result = specs.get(0);

    for (int i = 1; i < criterias.size(); i++) {
      result = Specification.where(result).and(specs.get(i));
    }
    return result;
  }
}
