package com.didihe1988.picker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EnterController {
	@RequestMapping(value="/enter")
	public String enter()
	{
		return "enter";
	}
	
	@RequestMapping(value=" ")
	public String defaul()
	{
		return "enter";
	}
}
