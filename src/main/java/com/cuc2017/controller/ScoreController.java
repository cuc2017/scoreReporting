package com.cuc2017.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cuc2017.model.Game;
import com.cuc2017.model.Team;
import com.cuc2017.service.GameService;
import com.cuc2017.service.TwitterService;

@Controller
public class ScoreController {

  private static final Logger log = LoggerFactory.getLogger(ScoreController.class);
  private static final String SCORE_FRAGMENTS_PATH = "fragments/scoreFragments ::";
  private static final String HOME_TEAMS_FRAGMENT = SCORE_FRAGMENTS_PATH + "addHomeTeams";
  private static final String AWAY_TEAMS_FRAGMENT = SCORE_FRAGMENTS_PATH + "addAwayTeams";
  private static final String SCORING_GAME_FRAGMENT = SCORE_FRAGMENTS_PATH + "scoreGame";

  private GameService gameService;
  private TwitterService twitterService;

  @RequestMapping("/score")
  public String restricted(HttpServletRequest request, Model model) {
    model.addAttribute("divisions", getGameService().getDivisions());
    model.addAttribute("fields", getGameService().getFields());
    return "score";
  }

  @RequestMapping(value = "/selectDivision", method = RequestMethod.POST, params = { "division" })
  public String getHomeTeams(@RequestParam("division") Long divisionId, HttpServletRequest request, Model model) {
    try {
      List<Team> teams = getGameService().getTeams(divisionId);
      log.info("Teams are: " + teams);
      model.addAttribute("homeTeams", teams);
      return HOME_TEAMS_FRAGMENT;
    } catch (Exception e) {
      log.error("Problem getting homes teams for division: " + divisionId, e);
      throw e;
    }
  }

  @RequestMapping(value = "/selectHomeTeam", method = RequestMethod.POST, params = { "division", "homeTeam" })
  public String getAwayTeams(@RequestParam("division") Long divisionId, @RequestParam("homeTeam") Long homeTeamId,
      HttpServletRequest request, Model model) {
    try {
      List<Team> teams = getGameService().getTeams(divisionId);
      log.info("Teams are: " + teams);
      for (Team team : teams) {
        if (team.getId() == homeTeamId) {
          teams.remove(team);
          break;
        }
      }
      model.addAttribute("awayTeams", teams);
      return AWAY_TEAMS_FRAGMENT;
    } catch (Exception e) {
      log.error("Problem getting homes teams for division: " + divisionId, e);
      throw e;
    }
  }

  @RequestMapping(value = "/selectGame", method = RequestMethod.POST, params = { "field", "division", "homeTeam",
      "awayTeam" })
  public String createGame(@RequestParam("division") Long divisionId, @RequestParam("homeTeam") Long homeTeamId,
      @RequestParam("awayTeam") Long awayTeamId, @RequestParam("field") Long fieldId, HttpServletRequest request,
      Model model) {
    try {
      Game game = getGameService().createGame(divisionId, homeTeamId, awayTeamId, fieldId);
      String readyforGameTweet = "Getting ready for: " + game.getGameTweetSummary();
      getTwitterService().tweet(readyforGameTweet);
      getTwitterService().tweetToField(game.getField(), readyforGameTweet);
      model.addAttribute("game", game);
      return SCORING_GAME_FRAGMENT;
    } catch (Exception e) {
      log.error("Problem creating game for field: " + fieldId, e);
      throw e;
    }
  }

  public GameService getGameService() {
    return gameService;
  }

  @Autowired
  public void setGameService(GameService gameService) {
    this.gameService = gameService;
  }

  public TwitterService getTwitterService() {
    return twitterService;
  }

  @Autowired
  public void setTwitterService(TwitterService twitterService) {
    this.twitterService = twitterService;
  }

}
