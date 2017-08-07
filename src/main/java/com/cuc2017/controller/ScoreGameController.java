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

	@RequestMapping(value = "/startGame", method = RequestMethod.POST, params = { "game" })
	public ResponseEntity<?> startGame(@RequestParam("game") Long gameId, HttpServletRequest request, Model model) {
		try {
			Game game = getGameService().startGame(gameId);
			String startingGameTweet = game.getLastEvent().tweetString() + game.getGameTweetSummary();
			getTwitterService().tweet(startingGameTweet);
			getTwitterService().tweetToField(game.getField(), startingGameTweet);
			return new ResponseEntity<Game>(game, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Problem start game for game: " + gameId, e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/endGame", method = RequestMethod.POST, params = { "game" })
	public ResponseEntity<?> endGame(@RequestParam("game") Long gameId, HttpServletRequest request, Model model) {
		try {
			Game game = getGameService().endGame(gameId);
			getTwitterService().tweet(game.getFinalGameTweet());
			getTwitterService().tweetToField(game.getField(), game.getFinalGameTweetField());
			return new ResponseEntity<Game>(game, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Problem ending game for game: " + gameId, e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/halftime", method = RequestMethod.POST, params = { "game" })
	public ResponseEntity<?> halftime(@RequestParam("game") Long gameId, HttpServletRequest request, Model model) {
		try {
			Game game = getGameService().halftime(gameId);

			getTwitterService().tweet(game.getHalftimeTweet());
			getTwitterService().tweetToField(game.getField(), game.getHalftimeTweetField());
			return new ResponseEntity<Game>(game, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Problem ending game for game: " + gameId, e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/undoEvent", method = RequestMethod.POST, params = { "event" })
	public ResponseEntity<?> undoEvent(@RequestParam("event") Long eventId, HttpServletRequest request, Model model) {
		try {
			Game game = getGameService().undoEvent(eventId);
			return new ResponseEntity<String>(game.getCurrentScore(), HttpStatus.OK);
		} catch (Exception e) {
			log.error("Problem undo event for: " + eventId, e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/pointScored", method = RequestMethod.POST, params = { "game", "team" })
	public ResponseEntity<?> pointScored(@RequestParam("game") Long gameId, @RequestParam("team") Long teamId,
			HttpServletRequest request) {
		try {
			Game game = getGameService().pointScored(gameId, teamId);
			getTwitterService().tweetToField(game.getField(), game.getCurrentGameTweet());
			return new ResponseEntity<String>(game.getCurrentScore(), HttpStatus.OK);
		} catch (Exception e) {
			log.error("Problem point scored: " + gameId + " for team " + teamId, e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/updateLastEvent", method = RequestMethod.GET, params = { "game" })
	public ResponseEntity<?> updateLastEvent(@RequestParam("game") Long gameId, HttpServletRequest request) {
		try {
			Game game = getGameService().getGame(gameId);
			return new ResponseEntity<String>(game.getLastEvent().eventAsHtmlRow(), HttpStatus.OK);
		} catch (Exception e) {
			log.error("Problem start game for game: " + gameId, e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/proposedFinalScore", method = RequestMethod.GET, params = { "game" })
	public ResponseEntity<?> propsoedFianlScore(@RequestParam("game") Long gameId, HttpServletRequest request,
			Model model) {
		try {
			Game game = getGameService().getGame(gameId);
			return new ResponseEntity<String>(game.getProposedFinalScore(), HttpStatus.OK);
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
