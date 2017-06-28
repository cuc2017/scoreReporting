package com.cuc2017.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cuc2017.service.GameService;

@Controller
public class ScoreController {

	// private static final Logger log =
	// LoggerFactory.getLogger(ScoreController.class);

	private GameService gameService;

	@RequestMapping("/score")
	String restricted(HttpServletRequest request, Model model) {
		model.addAttribute("divisions", getGameService().getDivisions());
		model.addAttribute("fields", getGameService().getFields());
		return "score";
	}

	public GameService getGameService() {
		return gameService;
	}

	@Autowired
	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

}
