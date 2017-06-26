package com.cuc2017;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.cuc2017.model.Division;
import com.cuc2017.model.Field;
import com.cuc2017.model.Game;
import com.cuc2017.model.Player;
import com.cuc2017.model.Team;
import com.cuc2017.repository.DivisionRepository;
import com.cuc2017.repository.FieldRepository;
import com.cuc2017.repository.GameRepository;
import com.cuc2017.repository.PlayerRepository;
import com.cuc2017.repository.TeamRepository;

@Component
public class DataLoader implements ApplicationRunner {

	private DivisionRepository divisionRepository;
	private FieldRepository fieldRepository;
	private TeamRepository teamRepository;
	private GameRepository gameRepository;
	private PlayerRepository playerRepository;

	@Override
	public void run(ApplicationArguments args) {
		Division openDivision = new Division("Open");
		getDivisionRepository().save(openDivision);
		Division womenDivision = new Division("Women");
		getDivisionRepository().save(womenDivision);
		List<Field> fields = new ArrayList<>(18);
		for (int i = 1; i <= 19; i++) {
			if (i != 3) {
				Field field = new Field("Field " + i);
				getFieldRepository().save(field);
				fields.add(field);
			}
		}
		Team team1 = new Team(openDivision, "team 1");
		getTeamRepository().save(team1);
		Team team2 = new Team(openDivision, "team 2");
		getTeamRepository().save(team2);
		Game game1 = new Game(team1, team2, fields.get(4));
		getGameRepository().save(game1);

		addPlayersForTeam(team1);
		addPlayersForTeam(team2);
	}

	private void addPlayersForTeam(Team team) {
		for (int i = 1; i < 22; i++) {
			getPlayerRepository().save(new Player(i, "Player", String.valueOf(i), team));
		}
	}

	public DivisionRepository getDivisionRepository() {
		return divisionRepository;
	}

	@Autowired
	public void setDivisionRepository(DivisionRepository divisionRepository) {
		this.divisionRepository = divisionRepository;
	}

	public FieldRepository getFieldRepository() {
		return fieldRepository;
	}

	@Autowired
	public void setFieldRepository(FieldRepository fieldRepository) {
		this.fieldRepository = fieldRepository;
	}

	public TeamRepository getTeamRepository() {
		return teamRepository;
	}

	@Autowired
	public void setTeamRepository(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

	public GameRepository getGameRepository() {
		return gameRepository;
	}

	@Autowired
	public void setGameRepository(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	public PlayerRepository getPlayerRepository() {
		return playerRepository;
	}

	@Autowired
	public void setPlayerRepository(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}
}
