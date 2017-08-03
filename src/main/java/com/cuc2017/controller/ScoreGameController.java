package com.cuc2017.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuc2017.model.Game;
import com.cuc2017.service.GameService;
import com.cuc2017.service.TwitterService;

@RestController
public class ScoreGameController {

  private static final Logger log = LoggerFactory.getLogger(ScoreGameController.class);

  private GameService gameService;
  private TwitterService twitterService;

  // @RequestMapping(value = "/selectGame", method = RequestMethod.POST,
  // params = { "field", "division", "homeTeam",
  // "awayTeam" })
  // public ResponseEntity<?> getGameFromField(@RequestParam("division") Long
  // divisionId,
  // @RequestParam("homeTeam") Long homeTeamId, @RequestParam("awayTeam") Long
  // awayTeamId,
  // @RequestParam("field") Long fieldId, HttpServletRequest request) {
  // try {
  // Game game = getGameService().createGame(divisionId, homeTeamId,
  // awayTeamId, fieldId);
  // String startingGameTweet = "Getting ready for : " + game.getHomeTeam() +
  // " vs " + game.getAwayTeam()
  // + " in " + game.getDivision().getHashtag() + " at " +
  // game.getField().getHashtag();
  // getTwitterService().tweet(startingGameTweet);
  // return new ResponseEntity<Game>(game, HttpStatus.OK);
  // } catch (Exception e) {
  // log.error("Problem getting game for field: " + fieldId, e);
  // return ResponseEntity.badRequest().body(e.getMessage());
  // }
  // }

  @RequestMapping(value = "/startGame", method = RequestMethod.POST, params = { "game" })
  public ResponseEntity<?> startGame(@RequestParam("game") Long gameId, HttpServletRequest request, Model model) {
    try {
      Game game = getGameService().getGame(gameId);
      String startingGameTweet = "Starting: " + game.getGameTweetSummary();
      getTwitterService().tweet(startingGameTweet);
      getTwitterService().tweetToField(game.getField(), startingGameTweet);
      return new ResponseEntity<Game>(game, HttpStatus.OK);
    } catch (Exception e) {
      log.error("Problem start game for game: " + gameId, e);
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @RequestMapping(value = "/pointScored", method = RequestMethod.POST, params = { "game", "team" })
  public ResponseEntity<?> pointScored(@RequestParam("game") Long gameId, @RequestParam("team") Long teamId,
      HttpServletRequest request, Model model) {
    try {
      Game game = getGameService().pointScored(gameId, teamId);
      getTwitterService().tweetToField(game.getField(), game.getCurrentGameTweet());
      return new ResponseEntity<String>(game.getCurrentScore(), HttpStatus.OK);
    } catch (Exception e) {
      log.error("Problem start game for game: " + gameId, e);
      return ResponseEntity.badRequest().body(e.getMessage());
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
