package com.cuc2017.service;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
	public static final SimpleDateFormat FORMATTER = new SimpleDateFormat("hh:mm");

	static {
		TimeZone timeZone = TimeZone.getTimeZone("America/New_York");
		FORMATTER.setTimeZone(timeZone);
	}

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
	public String eventAsHtmlRow(Event event) {
		StringBuffer row = new StringBuffer();
		eventAsHtmlRow(event, row, true, null);
		return row.toString();
	}

	private void loadPlayersForTeam(Team team, Game game) {
		loadPlayersFromUltimatCanadaSite(team, game);
		addAdditionalPlayesrForEachTeam(team, game);
	}

	private void loadPlayersFromUltimatCanadaSite(Team team, Game game) {
		try {
			URL url = new URL("http://80.172.224.48/cuc2017jr/?view=teamcard&team=" + team.getTeamNumber());
			Document page = Jsoup.parse(url, 5000);
			Element playerTable = page.select("table[style=width:80%]").first();
			Elements players = playerTable.select("tr");
			for (Element playerRow : players) {
				Element playerCell = playerRow.select("a[href]").first();
				if (playerCell != null) {
					String linkHref = playerCell.attr("href");
					String ultimateCanadaId = linkHref.substring(linkHref.indexOf("player=") + 7);
					String linkText = playerCell.text();
					int number = 0;
					if (linkText.startsWith("#")) {
						int firstSpace = linkText.indexOf(' ');
						number = Integer.parseInt(linkText.substring(1, firstSpace));
						linkText = linkText.substring(firstSpace + 1);
					}
					int firstSpace = linkText.indexOf(' ');
					String firstName = linkText.substring(0, firstSpace);
					String lastName = linkText.substring(firstSpace + 1);
					Player player = new Player(number, firstName, lastName, team, ultimateCanadaId, game);
					getPlayerRepository().save(player);
				}
			}
		} catch (Exception e) {
			log.error("Could not load players ", e);
		}
	}

	private void addAdditionalPlayesrForEachTeam(Team team, Game game) {
		getPlayerRepository().save(new Player(Player.UNKNOWN_PLAYER, "", "Unknown", team, game));
		getPlayerRepository().save(new Player(Player.CALLAHAN, "Gallahan", "Goal", team, game));
	}

	private void eventAsHtmlRow(Event event, StringBuffer row, boolean addSelect, Date gameStartTime) {
		row.append("<tr data-event-id='");
		row.append(event.getId());
		row.append("'>");
		switch (event.getEventType()) {
		case POINT_SCORED:
			row.append("<td>");
			row.append(event.getTeam().getName());
			row.append(" scored</td>");
			row.append("<td>");
			if (addSelect) {

				row.append("<select id='assist-");
				row.append(event.getId());
				row.append("' class='form-control' onchange='selectAssistBy(this)'>");
				addPlayers(event, row, event.getAssist(), true);
				row.append("</select>");
			} else {
				row.append(event.getAssist());
			}
			row.append("</td><td>");
			if (addSelect) {
				row.append("<select id='goal-");
				row.append(event.getId());
				row.append("' class='form-control' onchange='selectGoalBy(this)'>");
				addPlayers(event, row, event.getGoal(), false);
				row.append("</select>");
			} else {
				row.append(event.getGoal());
			}
			row.append("</td><td>");
			break;
		case TIME_OUT:
			row.append("<td colspan='3'>");
			row.append(event.getEventType().getName());
			row.append(" ");
			row.append(event.getTeam().getName());
			row.append("</td><td>");
			break;
		case STARTED:
		case HALF_TIME:
		case GAVE_OVER:
		case READY:
		default:
			row.append("<td colspan='3'>");
			row.append(event.getEventType().getName());
			row.append("</td><td>");
		}
		if (addSelect) {
			row.append(FORMATTER.format(event.getCreated()));

		} else {
			long duration = event.getCreated().getTime() - gameStartTime.getTime();
			long seconds = TimeUnit.MILLISECONDS.toSeconds(duration) % 60;
			long minutes = TimeUnit.MILLISECONDS.toMinutes(duration);
			row.append(minutes + " min " + seconds + " sec");
		}
		row.append("</td");
		row.append("</tr>");
	}

	private void addPlayers(Event event, StringBuffer row, Player selectedPlayer, boolean allowCallahan) {
		List<Player> players = getPlayers(event.getGame(), event.getTeam());
		for (Player player : players) {
			if (player.isCallahanPlayer() && !allowCallahan) {
				continue;
			}
			row.append("<option ");
			if (player.equals(selectedPlayer)) {
				row.append("selected='selected'");
			}
			row.append(" value='");
			row.append(player.getId());
			row.append("'>");
			row.append(player);
			row.append("</option>");
		}
	}

	@Override
	public Game createGame(Long divisionId, Long homeTeamId, Long awayTeamId, Long fieldId) {
		Division division = getDivisionRepository().findOne(divisionId);
		Team homeTeam = getTeam(homeTeamId);
		Team awayTeam = getTeam(awayTeamId);
		Field field = getFieldRepository().findOne(fieldId);
		Game game = new Game(division, homeTeam, awayTeam, field);
		saveGame(game);
		loadPlayersForTeam(homeTeam, game);
		loadPlayersForTeam(awayTeam, game);
		Event event = new Event(EventType.READY, game);
		addEvent(game, event);
		updateCurrentGame(game);
		return game;
	}

	private void updateCurrentGame(Game game) {
		for (CurrentGame currentGame : getCurrentGameRepository().findAll()) {
			Field currentGameField = currentGame.getGame().getField();
			Field gameField = game.getField();
			if (currentGameField.equals(gameField)) {
				currentGame.setGame(game);
				currentGame.setUseGame(true);
				getCurrentGameRepository().save(currentGame);
				return;
			}
		}
		getCurrentGameRepository().save(new CurrentGame(game.getField(), game));
	}

	@Override
	public boolean fieldInUse(Long fieldId) {
		for (CurrentGame currentGame : getCurrentGameRepository().findAll()) {
			if (currentGame.isUseGame()) {
				Game game = currentGame.getGame();
				if (game != null) {
					if (!game.isGameOver()) {
						Field field = game.getField();
						if (field != null && field.getId() == fieldId) {
							return true;
						}
					}
				}
			}
		}
		return false;
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
		game.resetTimeOuts();
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
		case TIME_OUT:
			if (event.getTeam().equals(game.getHomeTeam())) {
				getTwitterService().tweetToField(game.getField(),
						"Oops ... no timeout taken by " + game.getHomeTeam().getName());
				game.homeTeamUndoTookTimeOut();
			} else if (event.getTeam().equals(game.getAwayTeam())) {
				getTwitterService().tweetToField(game.getField(),
						"Oops ... no timeout taken by " + game.getAwayTeam().getName());
				game.awayTeamUndoTookTimeOut();
			} else {
				log.error("Team is not playing in this game!: " + event.getTeam());
				throw new Exception("Team is not playing in this game");
			}
			saveGame(game);
		case GAVE_OVER:
		case READY:
		case STARTED:
		default:
			break;
		}
		return game;
	}

	@Override
	public String updateAllEvents(Game game) {
		if (game == null) {
			return "";
		}
		StringBuffer buffer = new StringBuffer();

		Event startGameEvent = game.getStartGameEvent();
		Date startGameTime = null;
		if (startGameEvent != null) {
			startGameTime = startGameEvent.getCreated();
		}
		for (Event event : game.getEvents()) {
			if (event.isUseEvent() && event.getEventType() != EventType.READY) {
				eventAsHtmlRow(event, buffer, false, startGameTime);
			}
		}
		return buffer.toString();
	}

	@Override
	public Game pointScored(Long gameId, Long teamId) throws Exception {
		Team team = getTeam(teamId);
		Game game = getGame(gameId);
		Player unknownPlayer = getPlayerRepository().findByGameAndTeamAndNumber(game, team, Player.UNKNOWN_PLAYER);
		if (team.equals(game.getHomeTeam())) {
			game.incrementHomeTeamScore();
		} else if (team.equals(game.getAwayTeam())) {
			game.incrementAwayTeamScore();
		} else {
			log.error("Team is not playing in this game!: " + team);
			throw new Exception("Team is not playing in this game");
		}
		Event event = new Event(EventType.POINT_SCORED, game, team, unknownPlayer, unknownPlayer);
		addEvent(game, event);
		return game;
	}

	@Override
	public Game goal(Long eventId, Long scoredById) {
		Event event = getEventRepository().findOne(eventId);
		Player scoredBy = getPlayerRepository().findOne(scoredById);
		event.setGoal(scoredBy);
		getEventRepository().save(event);
		return event.getGame();
	}

	@Override
	public Game assist(Long eventId, Long assistedById) {
		Event event = getEventRepository().findOne(eventId);
		Player assistedBy = getPlayerRepository().findOne(assistedById);
		event.setAssist(assistedBy);
		getEventRepository().save(event);
		return event.getGame();
	}

	@Override
	public Game timeOut(Long gameId, Long teamId) throws Exception {
		Team team = getTeam(teamId);
		Game game = getGame(gameId);
		String timeOutsTakenString = "First ";
		if (team.equals(game.getHomeTeam())) {
			game.homeTeamTookTimeOut();
			if (game.getHomeTimeOutThisHalf() > 1) {
				timeOutsTakenString = "Second ";
			}
		} else if (team.equals(game.getAwayTeam())) {
			game.awayTeamTookTimeOut();
			if (game.getHomeTimeOutThisHalf() > 1) {
				timeOutsTakenString = "Second ";
			}
		} else {
			log.error("Team is not playing in this game!: " + team);
			throw new Exception("Team is not playing in this game");
		}
		Event event = new Event(EventType.TIME_OUT, game, team);
		addEvent(game, event);
		String half = "Timeout in the first half: ";
		if (game.hasHadHalfTime()) {
			half = "Timeout in the second half: ";
		}
		getTwitterService().tweetToField(game.getField(),
				timeOutsTakenString + half + team.getName() + " " + team.getDivision().getHashtag());
		return game;
	}

	private Team getTeam(Long teamId) {
		return getTeamRepository().findOne(teamId);
	}

	@Override
	public List<Player> getPlayers(Game game, Team team) {
		List<Player> players = getPlayerRepository().findByGameAndTeamOrderByNumberAsc(game, team);
		return players;
	}

	@Override
	public List<CurrentGame> getCurrentGames() {
		updateCurrentGames();
		List<CurrentGame> currentGames = getCurrentGameRepository().findAllByOrderByGame_Field_IdAsc();
		return currentGames;
	}

	private void updateCurrentGames() {
		List<CurrentGame> currentGames = (List<CurrentGame>) getCurrentGameRepository().findAll();
		for (CurrentGame currentGame : currentGames) {
			if (!currentGame.getGame().isRecent()) {
				currentGame.setUseGame(false);
				getCurrentGameRepository().save(currentGame);
			}
		}
	}

	@Override
	public List<Game> getAllGames() {
		return getGameRepository().findAllByOrderByIdDesc();
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
