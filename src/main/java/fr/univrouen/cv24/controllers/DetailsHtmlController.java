package fr.univrouen.cv24.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.univrouen.cv24.model.CV;
import fr.univrouen.cv24.repository.CVRepository;

@Controller
public class DetailsHtmlController {
	@Autowired
	private CVRepository cvRepository;
	
	@GetMapping("/cv24/html")
	public String getDetailsofCVXML(@RequestParam(value = "id") int id, Model model) {
		Optional<CV> optionalCV = cvRepository.findById((long) id);
		if(optionalCV.isPresent()) {
			CV cv = optionalCV.get();
			model.addAttribute("cv", cv);
			return "cvDetails";
		}else {
			model.addAttribute("id", id);
			model.addAttribute("status", "ERROR");
			return "error";
		}		
	}
}
