package com.itacademy.cms.model.dto;

import com.itacademy.cms.repository.specification.SearchCriteria;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteriaDto {
  private List<SearchCriteria> criteriaList = new ArrayList<>();
}
