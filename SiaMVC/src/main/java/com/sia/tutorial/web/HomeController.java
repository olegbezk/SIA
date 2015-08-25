package com.sia.tutorial.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping({"/", "/homepage"})
public class HomeController {

	@RequestMapping(method = GET)
	public String home() {
		return "home";
	}

}
