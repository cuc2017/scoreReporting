package com.cuc2017.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cuc2017.model.Field;

public interface FieldRepository extends PagingAndSortingRepository<Field, Long> {

	Field findByFieldName(String field);
}
