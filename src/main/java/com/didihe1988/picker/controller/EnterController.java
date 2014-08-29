package com.didihe1988.picker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EnterController {
	@RequestMapping(value = "/enter")
	public String enter(Model model) {
		return "enter";
	}

}
