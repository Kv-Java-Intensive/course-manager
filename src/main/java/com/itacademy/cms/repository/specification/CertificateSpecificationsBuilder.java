package com.itacademy.cms.repository.specification;

import com.itacademy.cms.model.Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;

public class CertificateSpecificationsBuilder {
  private final List<SearchCriteria> criterias;

  public CertificateSpecificationsBuilder() {
    criterias = new ArrayList<>();
  }

  public CertificateSpecificationsBuilder with(String key, String operation, String value) {
    criterias.add(new SearchCriteria(key, operation, value));
    return this;
  }

  public Specification<Certificate> build() {
    if (criterias.size() == 0) {
      return null;
    }

    List<Specification<Certificate>> specs = criterias.stream()
        .map(CertificateSpecification::new)
        .collect(Collectors.toList());

    Specification<Certificate> result = specs.get(0);

    for (int i = 1; i < criterias.size(); i++) {
      result = Specification.where(result).and(specs.get(i));
    }
    return result;
  }
}
