package com.cuc2017.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cuc2017.model.Team;

public interface TeamRepository extends PagingAndSortingRepository<Team, Long> {

}
