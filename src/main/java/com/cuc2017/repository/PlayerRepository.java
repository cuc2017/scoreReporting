package com.cuc2017.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cuc2017.model.Game;
import com.cuc2017.model.Player;
import com.cuc2017.model.Team;

public interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {
	List<Player> findByGameAndTeamOrderByNumberAsc(Game game, Team team);

	Player findByGameAndTeamAndNumber(Game game, Team team, int number);
}
