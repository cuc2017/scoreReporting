package com.cuc2017.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cuc2017.model.Event;

public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

}
