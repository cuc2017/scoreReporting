package com.cuc2017;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

@Component
public class DataLoader implements ApplicationRunner {

  private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

  private DivisionRepository divisionRepository;
  private FieldRepository fieldRepository;
  private TeamRepository teamRepository;
  private GameRepository gameRepository;
  private PlayerRepository playerRepository;

  @Override
  public void run(ApplicationArguments args) {

    if (getDivisionRepository().count() > 0) {
      return;
    }

    getFieldRepository().save(new Field("Field 1", "@CUC2017Field1"));
    getFieldRepository().save(new Field("Field 3", "@CUC2017Field3"));
    getFieldRepository().save(new Field("Field 4", "@CUC2017Field4"));
    getFieldRepository().save(new Field("Field 5", "@CUC2017Field5"));
    getFieldRepository().save(new Field("Field 6", "@CUC2017Field6"));
    getFieldRepository().save(new Field("Field 7", "@CUC2017Field7"));
    getFieldRepository().save(new Field("Field 8", "@CUC2017Field8"));
    getFieldRepository().save(new Field("Field 9", "@CUC2017Field9"));
    getFieldRepository().save(new Field("Field 10", "@CUC2017Field10"));// Check
                                                                        // Field
    getFieldRepository().save(new Field("Field 11", "@CUC2017Field11"));
    getFieldRepository().save(new Field("Field 12", "@CUC2017Field12"));
    getFieldRepository().save(new Field("Field 13", "@CUC2017Field13"));
    getFieldRepository().save(new Field("Field 14", "@CUC2017Field14"));
    getFieldRepository().save(new Field("Field 15", "@CUC2017Field15"));
    getFieldRepository().save(new Field("Field 16", "@CUC2017Field16"));
    getFieldRepository().save(new Field("Field 17", "@CUC2017Field17"));
    getFieldRepository().save(new Field("Field 18", "@CUC2017Field18"));
    getFieldRepository().save(new Field("Field 19", "@CUC2017Field19"));
    getFieldRepository().save(new Field("MNP Park", "@CUC2017MNPPark"));
    getFieldRepository().save(new Field("Field 21", "@CUC2017Fiel121"));
    getFieldRepository().save(new Field("Field 22", "@CUC2017Field22"));
    getFieldRepository().save(new Field("Field 23", "@CUC2017Field23"));
    getFieldRepository().save(new Field("Field 24", "@CUC2017Field24"));
    getFieldRepository().save(new Field("Field 25", ""));
    getFieldRepository().save(new Field("Field 26", ""));
    getFieldRepository().save(new Field("Field 27", ""));
    getFieldRepository().save(new Field("Field 28", ""));
    getFieldRepository().save(new Field("Field 29", ""));
    getFieldRepository().save(new Field("Field 30", ""));
    getFieldRepository().save(new Field("Field 31", ""));

    // loadAdultDivisions();
    loadJuniorDivisions();
    // Iterable<Team> teams = getTeamRepository().findAll();
    // for (Team team : teams) {
    // addAdditionalPlayesrForEachTeam(team);
    // }
    //
    // loadPlayersFromUltimatCanadaSite(teams);
    // loadPlayers(womenDivision, openDivision);
  }

  private void loadAdultDivisions() {
    Division openDivision = new Division("Open", "#CUC2017Open");
    getDivisionRepository().save(openDivision);
    Division womenDivision = new Division("Women", "#CUC2017Women");
    getDivisionRepository().save(womenDivision);
    Division masterOpenDivision = new Division("Masters Open", "#CUC2017MastersOpen");
    getDivisionRepository().save(masterOpenDivision);
    Division masterWomenDivision = new Division("Masters Women", "#CUC2017MastersWomen");
    getDivisionRepository().save(masterWomenDivision);

    Team team1 = new Team(openDivision, "Alberta Flatball Club", 23);
    getTeamRepository().save(team1);
    Team team2 = new Team(openDivision, "Blackfish", 24);
    getTeamRepository().save(team2);
    Team team3 = new Team(openDivision, "Club M - Manic", 25);
    getTeamRepository().save(team3);
    Team team4 = new Team(openDivision, "Critical Mass", 26);
    getTeamRepository().save(team4);
    Team team5 = new Team(openDivision, "DNA", 27);
    getTeamRepository().save(team5);
    Team team6 = new Team(openDivision, "General Strike", 28);
    getTeamRepository().save(team6);
    Team team7 = new Team(openDivision, "GOAT", 29);
    getTeamRepository().save(team7);
    Team team8 = new Team(openDivision, "Grand Trunk", 30);
    getTeamRepository().save(team8);
    Team team24 = new Team(openDivision, "Jerk Factory", 31);
    getTeamRepository().save(team24);
    Team team9 = new Team(openDivision, "Mockingbird", 32);
    getTeamRepository().save(team9);
    Team team10 = new Team(openDivision, "Phoenix", 33);
    getTeamRepository().save(team10);
    Team team11 = new Team(openDivision, "Refinery", 34);
    getTeamRepository().save(team11);
    Team team12 = new Team(openDivision, "Regiment", 35);
    getTeamRepository().save(team12);
    Team team13 = new Team(openDivision, "ROY", 36);
    getTeamRepository().save(team13);
    Team team14 = new Team(openDivision, "Sherbrooke Gentlemen", 37);
    getTeamRepository().save(team14);
    Team team15 = new Team(openDivision, "Whiskeyjacks", 38);
    getTeamRepository().save(team15);

    Team wteam11 = new Team(womenDivision, "6ixers", 39);
    getTeamRepository().save(wteam11);
    Team wteam12 = new Team(womenDivision, "Flurry", 40);
    getTeamRepository().save(wteam12);
    Team wteam13 = new Team(womenDivision, "Fusion", 41);
    getTeamRepository().save(wteam13);
    Team wteam14 = new Team(womenDivision, "Iris", 42);
    getTeamRepository().save(wteam14);
    Team wteam15 = new Team(womenDivision, "Korra", 43);
    getTeamRepository().save(wteam15);
    Team wteam16 = new Team(womenDivision, "PPF", 44);
    getTeamRepository().save(wteam16);
    Team wteam17 = new Team(womenDivision, "Qub", 45);
    getTeamRepository().save(wteam17);
    Team wteam18 = new Team(womenDivision, "Salty", 46);
    getTeamRepository().save(wteam18);
    Team wteam19 = new Team(womenDivision, "SHHippos", 47);
    getTeamRepository().save(wteam19);
    Team wteam22 = new Team(womenDivision, "Stella", 48);
    getTeamRepository().save(wteam22);
    Team wteam26 = new Team(womenDivision, "Storm", 49);
    getTeamRepository().save(wteam26);
    Team wteam20 = new Team(womenDivision, "Tempest", 50);
    getTeamRepository().save(wteam20);
    Team wteam21 = new Team(womenDivision, "Traffic", 51);
    getTeamRepository().save(wteam21);
    Team wteam23 = new Team(womenDivision, "Vénus", 52);
    getTeamRepository().save(wteam23);
    Team wteam24 = new Team(womenDivision, "Vesta", 53);
    getTeamRepository().save(wteam24);
    Team wteam25 = new Team(womenDivision, "Wendigo", 54);
    getTeamRepository().save(wteam25);

    Team masterteam1 = new Team(masterOpenDivision, "Best Before", 1);
    getTeamRepository().save(masterteam1);
    Team masterteam2 = new Team(masterOpenDivision, "Carbon", 2);
    getTeamRepository().save(masterteam2);
    Team masterteam3 = new Team(masterOpenDivision, "Iron Crow", 3);
    getTeamRepository().save(masterteam3);
    Team masterteam4 = new Team(masterOpenDivision, "Mangina", 4);
    getTeamRepository().save(masterteam4);
    Team masterteam5 = new Team(masterOpenDivision, "NSOM", 5);
    getTeamRepository().save(masterteam5);
    Team masterteam6 = new Team(masterOpenDivision, "NSOM 2", 6);
    getTeamRepository().save(masterteam6);
    Team masterteam7 = new Team(masterOpenDivision, "Quantum", 7);
    getTeamRepository().save(masterteam7);
    Team masterteam8 = new Team(masterOpenDivision, "Silverbacks", 8);
    getTeamRepository().save(masterteam8);
    Team masterteam24 = new Team(masterOpenDivision, "Torque", 9);
    getTeamRepository().save(masterteam24);
    Team masterteam9 = new Team(masterOpenDivision, "Viper", 10);
    getTeamRepository().save(masterteam9);

    Team masterwteam11 = new Team(masterWomenDivision, "Annie Oakley", 11);
    getTeamRepository().save(masterwteam11);
    Team masterwteam12 = new Team(masterWomenDivision, "Aurora", 12);
    getTeamRepository().save(masterwteam12);
    Team masterwteam13 = new Team(masterWomenDivision, "DYNA", 13);
    getTeamRepository().save(masterwteam13);
    Team masterwteam14 = new Team(masterWomenDivision, "Eclipse", 14);
    getTeamRepository().save(masterwteam14);
    Team masterwteam15 = new Team(masterWomenDivision, "lowercase", 15);
    getTeamRepository().save(masterwteam15);
    Team masterwteam16 = new Team(masterWomenDivision, "MINT", 16);
    getTeamRepository().save(masterwteam16);
    Team masterwteam17 = new Team(masterWomenDivision, "Nebula", 17);
    getTeamRepository().save(masterwteam17);
    Team masterwteam18 = new Team(masterWomenDivision, "Quasar", 18);
    getTeamRepository().save(masterwteam18);
    Team masterwteam19 = new Team(masterWomenDivision, "The Collective", 19);
    getTeamRepository().save(masterwteam19);
    Team masterwteam22 = new Team(masterWomenDivision, "THRIFT SHOP", 20);
    getTeamRepository().save(masterwteam22);
    Team masterwteam26 = new Team(masterWomenDivision, "Vintage", 21);
    getTeamRepository().save(masterwteam26);
    Team masterwteam20 = new Team(masterWomenDivision, "X-Squad", 22);
    getTeamRepository().save(masterwteam20);
  }

  private void loadJuniorDivisions() {
    Division openDivision = new Division("Junior Open", "#CUC2017JuniorOpen");
    getDivisionRepository().save(openDivision);
    Division womenDivision = new Division("Junior Women", "#CUC2017JuniorWomen");
    getDivisionRepository().save(womenDivision);

    Team team1 = new Team(openDivision, "Alpha BC", 1);
    getTeamRepository().save(team1);
    Team team2 = new Team(openDivision, "Bonfire", 2);
    getTeamRepository().save(team2);
    Team team3 = new Team(openDivision, "Cannons", 3);
    getTeamRepository().save(team3);
    Team team4 = new Team(openDivision, "Energy", 4);
    getTeamRepository().save(team4);
    Team team5 = new Team(openDivision, "High Tide", 5);
    getTeamRepository().save(team5);
    Team team6 = new Team(openDivision, "Hydro", 6);
    getTeamRepository().save(team6);
    Team team7 = new Team(openDivision, "Ignite", 7);
    getTeamRepository().save(team7);
    Team team8 = new Team(openDivision, "Manitou", 8);
    getTeamRepository().save(team8);
    Team team24 = new Team(openDivision, "Mischief", 10);
    getTeamRepository().save(team24);
    Team team9 = new Team(openDivision, "Misfit", 11);
    getTeamRepository().save(team9);
    Team team10 = new Team(openDivision, "MOFO", 9);
    getTeamRepository().save(team10);
    Team team11 = new Team(openDivision, "New Scotland Blues", 12);
    getTeamRepository().save(team11);
    Team team12 = new Team(openDivision, "NL Storm", 13);
    getTeamRepository().save(team12);
    Team team13 = new Team(openDivision, "Rogue Squadron", 14);
    getTeamRepository().save(team13);
    Team team14 = new Team(openDivision, "Rolling Thunder", 15);
    getTeamRepository().save(team14);
    Team team15 = new Team(openDivision, "Scorch", 16);
    getTeamRepository().save(team15);
    Team team16 = new Team(openDivision, "Spitfire", 17);
    getTeamRepository().save(team16);
    Team team17 = new Team(openDivision, "Stud", 18);
    getTeamRepository().save(team17);
    Team team18 = new Team(openDivision, "Titane", 19);
    getTeamRepository().save(team18);
    Team team19 = new Team(openDivision, "TORO", 20);
    getTeamRepository().save(team19);
    Team team20 = new Team(openDivision, "Uproar", 21);
    getTeamRepository().save(team20);
    Team team21 = new Team(openDivision, "Vortex", 22);
    getTeamRepository().save(team21);
    Team team22 = new Team(openDivision, "Wheaties", 23);
    getTeamRepository().save(team22);
    Team team23 = new Team(openDivision, "Wildfire", 24);
    getTeamRepository().save(team23);

    Team wteam11 = new Team(womenDivision, "Aera", 25);
    getTeamRepository().save(wteam11);
    Team wteam12 = new Team(womenDivision, "Bonfire", 26);
    getTeamRepository().save(wteam12);
    Team wteam13 = new Team(womenDivision, "Cannons", 27);
    getTeamRepository().save(wteam13);
    Team wteam14 = new Team(womenDivision, "High Tide", 28);
    getTeamRepository().save(wteam14);
    Team wteam15 = new Team(womenDivision, "Mischief", 29);
    getTeamRepository().save(wteam15);
    Team wteam16 = new Team(womenDivision, "Misfit", 30);
    getTeamRepository().save(wteam16);
    Team wteam17 = new Team(womenDivision, "MOFO", 31);
    getTeamRepository().save(wteam17);
    Team wteam18 = new Team(womenDivision, "New Scotland Blues", 32);
    getTeamRepository().save(wteam18);
    Team wteam19 = new Team(womenDivision, "Nightfury Soars", 33);
    getTeamRepository().save(wteam19);
    Team wteam22 = new Team(womenDivision, "Red River Rebellion", 34);
    getTeamRepository().save(wteam22);
    Team wteam26 = new Team(womenDivision, "Savage", 35);
    getTeamRepository().save(wteam26);
    Team wteam20 = new Team(womenDivision, "Titane", 36);
    getTeamRepository().save(wteam20);
    Team wteam21 = new Team(womenDivision, "TORO", 37);
    getTeamRepository().save(wteam21);
    Team wteam23 = new Team(womenDivision, "Uproar", 38);
    getTeamRepository().save(wteam23);
    Team wteam24 = new Team(womenDivision, "Vortex", 39);
    getTeamRepository().save(wteam24);
    Team wteam25 = new Team(womenDivision, "Wicked West", 40);
    getTeamRepository().save(wteam25);

  }

  private void loadPlayers(Division juniorWomen, Division juniorOpen) {
    URL rostersJson = DataLoader.class.getResource("/rosters/juniorRoster2.json");
    try (InputStream is = rostersJson.openStream()) {
      Gson gson = new Gson();
      JsonReader reader = new JsonReader(new InputStreamReader(is));
      reader.setLenient(true);
      List<Roster> rosters = gson.fromJson(reader, new TypeToken<List<Roster>>() {
      }.getType());
      for (Roster roster : rosters) {
        if (roster.isPlayer()) {
          Division division = juniorOpen;
          if (roster.getDivision().contains("omen")) {
            division = juniorWomen;
          }
          Team team = getTeamRepository().findByDivisionAndName(division, roster.getTeam_name());
          if (team == null) {
            log.error("Could not find team for: " + roster);
            continue;
          }
          Player player = new Player(roster.getUniform_number(), roster.getFirst_name(), roster.getLast_name(), team);
          getPlayerRepository().save(player);
        }
      }
    } catch (Exception e) {
      log.error("Problem loading players", e);
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
