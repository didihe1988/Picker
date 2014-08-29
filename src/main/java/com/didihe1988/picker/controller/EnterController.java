package com.didihe1988.picker.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnterController {
	@RequestMapping(value = "/enter")
	public String enter(Model model) {
		return "enter";
	}

	@RequestMapping(value = "/staticPage", method = RequestMethod.GET)
	public String redirect() {
		return "redirect:/pages/test.html";
	}

	/*
	 * @RequestMapping(value = "/test", method = RequestMethod.GET) public
	 * String redirect() { return "redirect:/pages/test.html"; }
	 */
}
