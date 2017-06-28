package com.cuc2017.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cuc2017.model.Division;

public interface DivisionRepository extends PagingAndSortingRepository<Division, Long> {

	Division findByName(String name);

	List<Division> findAllByOrderByNameAsc();
}
