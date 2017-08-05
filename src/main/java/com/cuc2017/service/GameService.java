package com.cuc2017.service;

import java.util.List;

import com.cuc2017.model.CurrentGame;
import com.cuc2017.model.Division;
import com.cuc2017.model.Field;
import com.cuc2017.model.Game;
import com.cuc2017.model.Team;

public interface GameService {
	List<Division> getDivisions();

	List<Team> getTeams(Long divisionId);

	List<Field> getFields();

	Game createGame(Long divisionId, Long homeTeamId, Long awayTeamId, Long fieldIds);

	Game getGame(Long gameId);

	Game pointScored(Long gameId, Long homeTeamId) throws Exception;

	Game startGame(Long gameId);

	Game endGame(Long gameId);

	List<CurrentGame> getCurrentGames();

}
