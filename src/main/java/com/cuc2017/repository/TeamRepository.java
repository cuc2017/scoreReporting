package com.cuc2017.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cuc2017.model.Division;
import com.cuc2017.model.Team;

public interface TeamRepository extends PagingAndSortingRepository<Team, Long> {
  List<Team> findByDivision_Id(Long id);

  Team findByDivisionAndName(Division division, String name);
}
