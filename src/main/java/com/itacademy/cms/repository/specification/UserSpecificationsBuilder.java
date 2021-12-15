package com.itacademy.cms.repository.specification;

import com.itacademy.cms.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecificationsBuilder {
  private final List<SearchCriteria> criterias;

  public UserSpecificationsBuilder() {
    criterias = new ArrayList<>();
  }

  public UserSpecificationsBuilder with(String key, String operation, String value) {
    criterias.add(new SearchCriteria(key, operation, value));
    return this;
  }

  public Specification<User> build() {
    if (criterias.size() == 0) {
      return null;
    }

    List<Specification<User>> specs = criterias.stream()
        .map(UserSpecification::new)
        .collect(Collectors.toList());

    Specification<User> result = specs.get(0);

    for (int i = 1; i < criterias.size(); i++) {
      result = Specification.where(result).and(specs.get(i));
    }
    return result;
  }
}
