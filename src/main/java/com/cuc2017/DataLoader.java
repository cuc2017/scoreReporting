package com.cuc2017;

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
		for (int i = 1; i <= 19; i++) {
			if (i != 2) {
				Field field = new Field("Field " + i, "@TeCUC2017Field" + i);
				getFieldRepository().save(field);
			}
		}
		Field field = new Field("MNP Park", "@TestCUC2017MNPPark");
		getFieldRepository().save(field);
		Team team1 = new Team(openDivision, "Alpha");
		getTeamRepository().save(team1);
		Team team2 = new Team(openDivision, "Bonfire");
		getTeamRepository().save(team2);
		Team team3 = new Team(openDivision, "Cannons");
		getTeamRepository().save(team3);
		Team team4 = new Team(openDivision, "Energy");
		getTeamRepository().save(team4);
		Team team5 = new Team(openDivision, "Hide Tide");
		getTeamRepository().save(team5);
		Team team6 = new Team(openDivision, "Hydro");
		getTeamRepository().save(team6);
		Team team7 = new Team(openDivision, "Ignite");
		getTeamRepository().save(team7);
		Team team8 = new Team(openDivision, "Manitou");
		getTeamRepository().save(team8);
		Team team24 = new Team(openDivision, "Mischief");
		getTeamRepository().save(team24);
		Team team9 = new Team(openDivision, "Misfit");
		getTeamRepository().save(team9);
		Team team10 = new Team(openDivision, "MOFO");
		getTeamRepository().save(team10);
		Team team11 = new Team(openDivision, "New Scotland Blues");
		getTeamRepository().save(team11);
		Team team12 = new Team(openDivision, "NL Storm");
		getTeamRepository().save(team12);
		Team team13 = new Team(openDivision, "Rogue Squadron");
		getTeamRepository().save(team13);
		Team team14 = new Team(openDivision, "Rolling Thunder");
		getTeamRepository().save(team14);
		Team team15 = new Team(openDivision, "Scorch");
		getTeamRepository().save(team15);
		Team team16 = new Team(openDivision, "Spitfire");
		getTeamRepository().save(team16);
		Team team17 = new Team(openDivision, "Stud");
		getTeamRepository().save(team17);
		Team team18 = new Team(openDivision, "Titane");
		getTeamRepository().save(team18);
		Team team19 = new Team(openDivision, "TORO");
		getTeamRepository().save(team19);
		Team team20 = new Team(openDivision, "Uproar");
		getTeamRepository().save(team20);
		Team team21 = new Team(openDivision, "Vortex");
		getTeamRepository().save(team21);
		Team team22 = new Team(openDivision, "Wheaties");
		getTeamRepository().save(team22);
		Team team23 = new Team(openDivision, "Wildfire");
		getTeamRepository().save(team23);

		Team wteam11 = new Team(womenDivision, "Aera");
		getTeamRepository().save(wteam11);
		Team wteam12 = new Team(womenDivision, "Bonfire");
		getTeamRepository().save(wteam12);
		Team wteam13 = new Team(womenDivision, "Cannons");
		getTeamRepository().save(wteam13);
		Team wteam14 = new Team(womenDivision, "High Tide");
		getTeamRepository().save(wteam14);
		Team wteam15 = new Team(womenDivision, "Mischief");
		getTeamRepository().save(wteam15);
		Team wteam16 = new Team(womenDivision, "Misfit");
		getTeamRepository().save(wteam16);
		Team wteam17 = new Team(womenDivision, "MOFO");
		getTeamRepository().save(wteam17);
		Team wteam18 = new Team(womenDivision, "New Scotland Blues");
		getTeamRepository().save(wteam18);
		Team wteam19 = new Team(womenDivision, "Nightfury Soars");
		getTeamRepository().save(wteam19);
		Team wteam22 = new Team(womenDivision, "Rebellion");
		getTeamRepository().save(wteam22);
		Team wteam26 = new Team(womenDivision, "Savage");
		getTeamRepository().save(wteam26);
		Team wteam20 = new Team(womenDivision, "Titane");
		getTeamRepository().save(wteam20);
		Team wteam21 = new Team(womenDivision, "TORO");
		getTeamRepository().save(wteam21);
		Team wteam23 = new Team(womenDivision, "Uproar");
		getTeamRepository().save(wteam23);
		Team wteam24 = new Team(womenDivision, "Vortex");
		getTeamRepository().save(wteam24);
		Team wteam25 = new Team(womenDivision, "Wicked West");
		getTeamRepository().save(wteam25);

		for (Team team : getTeamRepository().findAll()) {
			addPlayersForTeam(team);
		}
	}

	private void addPlayersForTeam(Team team) {
		for (int i = 22; i > 0; i--) {
			getPlayerRepository().save(new Player(i, team.getName(), "Player " + String.valueOf(i), team));
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
