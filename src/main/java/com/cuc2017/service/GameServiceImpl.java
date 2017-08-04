package com.cuc2017.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuc2017.model.Division;
import com.cuc2017.model.Field;
import com.cuc2017.model.Game;
import com.cuc2017.model.Team;
import com.cuc2017.repository.DivisionRepository;
import com.cuc2017.repository.FieldRepository;
import com.cuc2017.repository.GameRepository;
import com.cuc2017.repository.TeamRepository;

@Service
public class GameServiceImpl implements GameService {

	private static final Logger log = LoggerFactory.getLogger(GameServiceImpl.class);

	private DivisionRepository divisionRepository;
	private TeamRepository teamRepository;
	private FieldRepository fieldRepository;
	private GameRepository gameRepository;

	@Override
	public List<Division> getDivisions() {
		return getDivisionRepository().findAllByOrderByNameAsc();
	}

	@Override
	public List<Team> getTeams(Long divisionId) {
		return getTeamRepository().findByDivision_Id(divisionId);
	}

	@Override
	public List<Field> getFields() {
		return (List<Field>) getFieldRepository().findAll();
	}

	@Override
	public Game createGame(Long divisionId, Long homeTeamId, Long awayTeamId, Long fieldId) {
		Division division = getDivisionRepository().findOne(divisionId);
		Team homeTeam = getTeamRepository().findOne(homeTeamId);
		Team awayTeam = getTeamRepository().findOne(awayTeamId);
		Field field = getFieldRepository().findOne(fieldId);
		Game game = new Game(division, homeTeam, awayTeam, field);
		saveGame(game);
		return game;
	}

	@Override
	public Game endGame(Long gameId) {
		Game game = getGame(gameId);
		game.setGameOver(true);
		saveGame(game);
		return game;
	}

	private void saveGame(Game game) {
		getGameRepository().save(game);
	}

	@Override
	public Game pointScored(Long gameId, Long teamId) throws Exception {
		Team team = getTeamRepository().findOne(teamId);
		Game game = getGame(gameId);
		if (team.equals(game.getHomeTeam())) {
			game.incrementHomeTeamScore();
		} else if (team.equals(game.getAwayTeam())) {
			game.incrementAwayTeamScore();
		} else {
			log.error("Team is not playing in this game!: " + team);
			throw new Exception("Team is not playing in this game");
		}
		saveGame(game);
		return game;
	}

	@Override
	public Game getGame(Long gameId) {
		return getGameRepository().findOne(gameId);
	}

	public FieldRepository getFieldRepository() {
		return fieldRepository;
	}

	@Autowired
	public void setFieldRepository(FieldRepository fieldRepository) {
		this.fieldRepository = fieldRepository;
	}

	public GameRepository getGameRepository() {
		return gameRepository;
	}

	@Autowired
	public void setGameRepository(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	public DivisionRepository getDivisionRepository() {
		return divisionRepository;
	}

	@Autowired
	public void setDivisionRepository(DivisionRepository divisionRepository) {
		this.divisionRepository = divisionRepository;
	}

	public TeamRepository getTeamRepository() {
		return teamRepository;
	}

	@Autowired
	public void setTeamRepository(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

}
