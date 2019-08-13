package com.cuc2017;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
import com.cuc2017.service.GameServiceImpl;
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

    getFieldRepository().save(new Field("Field 1", ""));
    getFieldRepository().save(new Field("Field 2", ""));
    getFieldRepository().save(new Field("Field 3", ""));
    getFieldRepository().save(new Field("Field 4", ""));
    getFieldRepository().save(new Field("Field 5", ""));
    getFieldRepository().save(new Field("Field 6", ""));
    getFieldRepository().save(new Field("Field 7", ""));
    getFieldRepository().save(new Field("Field 8", ""));
    getFieldRepository().save(new Field("Field 9", ""));
    getFieldRepository().save(new Field("Field 10", ""));
    getFieldRepository().save(new Field("Field 11", ""));
    getFieldRepository().save(new Field("Field 12", ""));
    getFieldRepository().save(new Field("Field 13", ""));
    getFieldRepository().save(new Field("Field 14", ""));
    getFieldRepository().save(new Field("Field 15", ""));
    getFieldRepository().save(new Field("Field 16", ""));
    getFieldRepository().save(new Field("Field 17", ""));
    getFieldRepository().save(new Field("Field 18", ""));
    getFieldRepository().save(new Field("Field 19", ""));
    getFieldRepository().save(new Field("Field 20", ""));
    getFieldRepository().save(new Field("Field 21", ""));

    // TODO: Set proper division load
    // loadAdultDivisions();
    loadJuniorDivisions();
  }

  private void loadAdultDivisions() {
    Division openDivision = new Division("Open", 2, "CUC2018 Open");
    getDivisionRepository().save(openDivision);
    Division womenDivision = new Division("Women", 1, "CUC2018 Women");
    getDivisionRepository().save(womenDivision);
    Division masterOpenDivision = new Division("Masters Open", 4, "CUC2018 MastersOpen");
    getDivisionRepository().save(masterOpenDivision);
    Division masterWomenDivision = new Division("Masters Women", 3, "CUC2018 MastersWomen");
    getDivisionRepository().save(masterWomenDivision);

    loadTeamsFromUltimatCanadaSite(openDivision);
    loadTeamsFromUltimatCanadaSite(womenDivision);
    loadTeamsFromUltimatCanadaSite(masterOpenDivision);
    loadTeamsFromUltimatCanadaSite(masterWomenDivision);
  }

  private void loadTeamsFromUltimatCanadaSite(Division division) {
    try {
      URL url = new URL(GameServiceImpl.DIVISION_CARDS + division.getSeries());
      Document page = Jsoup.parse(url, 5000);
      Element teamTable = page.select("table[style=width:100%]").first();
      Elements teams = teamTable.select("tr");
      for (int i = 1; i < teams.size(); i++) {
        Element teamRow = teams.get(i);
        Element teamCell = teamRow.select("a[href]").first();
        if (teamCell != null) {
          String linkHref = teamCell.attr("href");
          String teamName = teamCell.text();
          String ultimateCanadaId = linkHref.substring(linkHref.indexOf("team=") + 5);
          int teamNumber = -1;
          try {
            teamNumber = Integer.valueOf(ultimateCanadaId);
          } catch (Exception e) {
            log.error("Could not parse team number [" + ultimateCanadaId + "] for team: " + teamName, e);
            continue;
          }

          Team team = new Team(division, teamName, teamNumber);
          getTeamRepository().save(team);
          log.info("Added team: " + team + " " + team.getTeamNumber());
        }
      }
    } catch (Exception e) {
      log.error("Could not load teams ", e);
    }
  }

  private void loadJuniorDivisions() {
    Division openDivision = new Division("Junior Open", 1, "CUC2018 JuniorOpen");
    getDivisionRepository().save(openDivision);
    Division womenDivision = new Division("Junior Women", 2, "CUC2018 JuniorWomen");
    getDivisionRepository().save(womenDivision);

    loadTeamsFromUltimatCanadaSite(openDivision);
    loadTeamsFromUltimatCanadaSite(womenDivision);
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
