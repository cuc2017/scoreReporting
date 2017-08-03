package com.cuc2017.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cuc2017.model.Point;
import com.cuc2017.model.Team;

public interface PointRepository extends PagingAndSortingRepository<Point, Long> {
  List<Point> findByGame_Id(Long id);
}
