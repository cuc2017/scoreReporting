package com.cuc2017.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cuc2017.model.Game;

public interface GameRepository extends PagingAndSortingRepository<Game, Long> {
	List<Game> findAllByOrderByIdDesc();
}
