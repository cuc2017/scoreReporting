package com.cuc2017;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.cuc2017.model.Division;
import com.cuc2017.model.Field;
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
		Division openDivision = new Division("Junior Open", "#TestCUC2017JuniorOpen");
		getDivisionRepository().save(openDivision);
		Division womenDivision = new Division("Junior Women", "#TestCUC2017JuniorWomen");
		getDivisionRepository().save(womenDivision);
		List<Field> fields = new ArrayList<>(18);
		for (int i = 1; i <= 19; i++) {
			if (i != 3) {
				Field field = new Field("Field " + i, "#TestCUC2017Field" + 1);
				getFieldRepository().save(field);
				fields.add(field);
			}
		}
		Team team1 = new Team(openDivision, "team 1");
		getTeamRepository().save(team1);
		Team team2 = new Team(openDivision, "team 2");
		getTeamRepository().save(team2);
		Team team3 = new Team(openDivision, "team 3");
		getTeamRepository().save(team3);
		Team team4 = new Team(openDivision, "team 4");
		getTeamRepository().save(team4);
		Team team5 = new Team(openDivision, "team 5");
		getTeamRepository().save(team5);
		Team team6 = new Team(openDivision, "team 6");
		getTeamRepository().save(team6);

		Team team11 = new Team(womenDivision, "team 11");
		getTeamRepository().save(team11);
		Team team12 = new Team(womenDivision, "team 12");
		getTeamRepository().save(team12);
		Team team13 = new Team(womenDivision, "team 13");
		getTeamRepository().save(team13);
		Team team14 = new Team(womenDivision, "team 14");
		getTeamRepository().save(team14);
		Team team15 = new Team(womenDivision, "team 15");
		getTeamRepository().save(team15);
		Team team16 = new Team(womenDivision, "team 16");
		getTeamRepository().save(team16);

		addPlayersForTeam(team1);
		addPlayersForTeam(team2);
		addPlayersForTeam(team3);
		addPlayersForTeam(team4);
		addPlayersForTeam(team5);
		addPlayersForTeam(team6);
		addPlayersForTeam(team11);
		addPlayersForTeam(team12);
		addPlayersForTeam(team13);
		addPlayersForTeam(team14);
		addPlayersForTeam(team15);
		addPlayersForTeam(team16);
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
