package com.itacademy.cms.repository.specification;

import com.itacademy.cms.model.Course;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class CourseSpecification implements Specification<Course> {
  private SearchCriteria criteria;

  @Override
  public Predicate toPredicate(@NonNull Root<Course> root, @NonNull CriteriaQuery<?> query,
                               @NonNull CriteriaBuilder builder) {
    if (criteria.getOperation().equalsIgnoreCase(">")) {
      return builder.greaterThanOrEqualTo(
          root.<String>get(criteria.getKey()), criteria.getValue().toString());
    } else if (criteria.getOperation().equalsIgnoreCase("<")) {
      return builder.lessThanOrEqualTo(
          root.<String>get(criteria.getKey()), criteria.getValue().toString());
    } else if (criteria.getOperation().equalsIgnoreCase("=")) {
      return builder.equal(root.get(criteria.getKey()), criteria.getValue());
    } else {
      return builder.like(
          root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
    }
  }
}
