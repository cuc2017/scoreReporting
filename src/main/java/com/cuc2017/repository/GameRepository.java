package com.cuc2017.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cuc2017.model.Field;
import com.cuc2017.model.Game;

public interface GameRepository extends PagingAndSortingRepository<Game, Long> {

	Game findByField(Field field);
}