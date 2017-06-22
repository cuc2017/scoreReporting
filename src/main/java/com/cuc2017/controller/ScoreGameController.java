package com.cuc2017.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuc2017.model.Game;

@RestController
public class ScoreGameController {

	private static final Logger log = LoggerFactory.getLogger(ScoreGameController.class);

	// private FieldService fieldService;

	@RequestMapping(value = "/selectGame", method = RequestMethod.POST, params = { "field" })
	public ResponseEntity<?> saveProjectPermission(@RequestParam("field") Integer fieldNumber,
			HttpServletRequest request) {
		try {
			if (fieldNumber == 2) {
				return ResponseEntity.badRequest().body("No game on field " + fieldNumber);
			}
			Game game = new Game("Team 1", "Team 2");
			return new ResponseEntity<Game>(game, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
