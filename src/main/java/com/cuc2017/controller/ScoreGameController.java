package com.cuc2017.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuc2017.model.Game;
import com.cuc2017.model.Team;
import com.cuc2017.service.GameService;

@RestController
public class ScoreGameController {

	private static final Logger log = LoggerFactory.getLogger(ScoreGameController.class);

	private GameService gameService;

	@RequestMapping(value = "/selectGame", method = RequestMethod.POST, params = { "field", "division", "homeTeam",
			"awayTeam" })
	public ResponseEntity<?> getGameFromField(@RequestParam("division") Long divisionId,
			@RequestParam("homeTeam") Long homeTeamId, @RequestParam("awayTeam") Long awayTeamId,
			@RequestParam("field") Long fieldId, HttpServletRequest request) {
		try {
			Game game = getGameService().createGame(divisionId, homeTeamId, awayTeamId, fieldId);
			log.info("Game is: " + game);
			return new ResponseEntity<Game>(game, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Problem getting game for field: " + fieldId, e);
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@RequestMapping(value = "/selectDivision", method = RequestMethod.POST, params = { "division" })
	public ResponseEntity<?> getTeams(@RequestParam("division") Long divisionId, HttpServletRequest request) {
		try {
			List<Team> teams = getGameService().getTeams(divisionId);
			log.info("Teams are: " + teams);
			return new ResponseEntity<List<Team>>(teams, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Problem getting teams for division: " + divisionId, e);
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

}
