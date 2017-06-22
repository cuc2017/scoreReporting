package com.cuc2017.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScoreController {

	private static final Logger log = LoggerFactory.getLogger(ScoreController.class);

	@RequestMapping("/score")
	String restricted(HttpServletRequest request, Model model) {
		List<Integer> fields = new ArrayList<>(18);
		for (int i = 1; i < 19; i++) {
			if (i != 3) {
				fields.add(i);
			}
		}
		model.addAttribute("fields", fields);
		return "score";
	}

}
