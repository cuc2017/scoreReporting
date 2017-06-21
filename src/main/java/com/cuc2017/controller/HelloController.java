package com.cuc2017.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cuc2017.service.HelloService;
import com.stormpath.sdk.servlet.account.AccountResolver;

@Controller
public class HelloController {

	@Autowired
	private HelloService helloService;

	@Autowired
	AccountResolver accountResolver;

	@RequestMapping("/")
	String home(HttpServletRequest request) {
		return "home";
	}

	@RequestMapping("/restricted")
	String restricted(HttpServletRequest request, Model model) {
		String msg = helloService.sayHello(accountResolver.getAccount(request));
		model.addAttribute("msg", msg);
		return "restricted";
	}
}