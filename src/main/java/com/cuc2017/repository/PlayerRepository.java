package com.cuc2017.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cuc2017.model.Player;
import com.cuc2017.model.Team;

public interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {
	Player findByTeam(Team team);
}
