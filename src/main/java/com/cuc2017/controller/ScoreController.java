package com.cuc2017.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cuc2017.repository.FieldRepository;

@Controller
public class ScoreController {

	private static final Logger log = LoggerFactory.getLogger(ScoreController.class);

	private FieldRepository fieldRepository;

	@RequestMapping("/score")
	String restricted(HttpServletRequest request, Model model) {
		model.addAttribute("fields", getFieldRepository().findAll());
		return "score";
	}

	public FieldRepository getFieldRepository() {
		return fieldRepository;
	}

	@Autowired
	public void setFieldRepository(FieldRepository fieldRepository) {
		this.fieldRepository = fieldRepository;
	}

}
