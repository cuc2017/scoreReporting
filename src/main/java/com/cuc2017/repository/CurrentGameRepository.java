package com.cuc2017.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cuc2017.model.CurrentGame;
import com.cuc2017.model.Field;

public interface CurrentGameRepository extends PagingAndSortingRepository<CurrentGame, Long> {
	CurrentGame findByField(Field field);

	List<CurrentGame> findAllByOrderByGame_Field_IdAsc();
}
