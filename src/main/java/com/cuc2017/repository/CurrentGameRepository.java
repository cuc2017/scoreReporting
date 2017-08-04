package com.cuc2017.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cuc2017.model.CurrentGame;

public interface CurrentGameRepository extends PagingAndSortingRepository<CurrentGame, Long> {
  CurrentGame findByField_Id(Long id);

  List<CurrentGame> findAllByOrderByField_IdAsc();
}
