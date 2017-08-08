package com.cuc2017.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuc2017.model.CurrentGame;
import com.cuc2017.model.Division;
import com.cuc2017.model.Event;
import com.cuc2017.model.EventType;
import com.cuc2017.model.Field;
import com.cuc2017.model.Game;
import com.cuc2017.model.Player;
import com.cuc2017.model.Team;
import com.cuc2017.repository.CurrentGameRepository;
import com.cuc2017.repository.DivisionRepository;
import com.cuc2017.repository.EventRepository;
import com.cuc2017.repository.FieldRepository;
import com.cuc2017.repository.GameRepository;
import com.cuc2017.repository.PlayerRepository;
import com.cuc2017.repository.TeamRepository;

@Service
public class GameServiceImpl implements GameService {

	private static final Logger log = LoggerFactory.getLogger(GameServiceImpl.class);

	private DivisionRepository divisionRepository;
	private TeamRepository teamRepository;
	private FieldRepository fieldRepository;
	private GameRepository gameRepository;
	private PlayerRepository playerRepository;
	private CurrentGameRepository currentGameRepository;
	private EventRepository eventRepository;
	private TwitterService twitterService;

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
		Event event = new Event(EventType.READY, game);
		addEvent(game, event);
		updateCurrentGame(game);
		return game;
	}

	private void updateCurrentGame(Game game) {
		CurrentGame currentGame = getCurrentGameRepository().findByField_Id(game.getField().getId());
		if (currentGame == null) {
			currentGame = new CurrentGame(game.getField(), game);
		} else {
			currentGame.setGame(game);
		}
		getCurrentGameRepository().save(currentGame);
	}

	@Override
	public Game startGame(Long gameId) {
		Game game = getGame(gameId);
		Event event = new Event(EventType.STARTED, game);
		addEvent(game, event);
		return game;
	}

	@Override
	public Game endGame(Long gameId) {
		Game game = getGame(gameId);
		Event event = new Event(EventType.GAVE_OVER, game);
		addEvent(game, event);
		return game;
	}

	@Override
	public Game halftime(Long gameId) {
		Game game = getGame(gameId);
		Event event = new Event(EventType.HALF_TIME, game);
		addEvent(game, event);
		return game;
	}

	private void addEvent(Game game, Event event) {
		saveEvent(event);
		game.addEvent(event);
		saveGame(game);
	}

	private void saveGame(Game game) {
		getGameRepository().save(game);
	}

	private void saveEvent(Event event) {
		getEventRepository().save(event);
	}

	@Override
	public Game undoEvent(Long eventId) throws Exception {
		Event event = getEventRepository().findOne(eventId);
		event.setUseEvent(false);
		saveEvent(event);
		Game game = event.getGame();
		switch (event.getEventType()) {
		case POINT_SCORED:
			if (event.getTeam().equals(game.getHomeTeam())) {
				game.decrementHomeTeamScore();
			} else if (event.getTeam().equals(game.getAwayTeam())) {
				game.decrementAwayTeamScore();
			} else {
				log.error("Team is not playing in this game!: " + event.getTeam());
				throw new Exception("Team is not playing in this game");
			}
			saveGame(game);
			getTwitterService().tweetToField(game.getField(), "Oops ... score is really " + game.getCurrentGameTweet());
			break;
		case HALF_TIME:
			getTwitterService().tweetToField(game.getField(),
					"Oops ... not halftime yet " + game.getCurrentGameTweet());
			getTwitterService()
					.tweet("Oops ... not halftime yet " + game.getCurrentGameTweet() + game.addFieldInfoToTweet());
			break;
		case SCORED_BY:
		case GAVE_OVER:
		case READY:
		case STARTED:
		default:
			break;
		}
		return game;
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
		Event event = new Event(EventType.POINT_SCORED, game, team);
		addEvent(game, event);
		return game;
	}

	@Override
	public List<Player> getPlayers(Team team) {
		return getPlayerRepository().findByTeamOrderByNumberAsc(team);
	}

	@Override
	public List<CurrentGame> getCurrentGames() {
		List<CurrentGame> currentGames = getCurrentGameRepository().findAllByOrderByField_IdAsc();
		return currentGames;
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

	public CurrentGameRepository getCurrentGameRepository() {
		return currentGameRepository;
	}

	@Autowired
	public void setCurrentGameRepository(CurrentGameRepository currentGameRepository) {
		this.currentGameRepository = currentGameRepository;
	}

	public EventRepository getEventRepository() {
		return eventRepository;
	}

	@Autowired
	public void setEventRepository(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	public TwitterService getTwitterService() {
		return twitterService;
	}

	@Autowired
	public void setTwitterService(TwitterService twitterService) {
		this.twitterService = twitterService;
	}

	public PlayerRepository getPlayerRepository() {
		return playerRepository;
	}

	@Autowired
	public void setPlayerRepository(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

}
