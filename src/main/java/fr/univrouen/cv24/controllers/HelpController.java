package fr.univrouen.cv24.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelpController {
	@GetMapping("/help")
	public String displayHelp() {
		return "help";
	}

}
