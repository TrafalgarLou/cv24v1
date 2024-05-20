package fr.univrouen.cv24.controllers;

import java.io.StringWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import fr.univrouen.cv24.model.CV;
import fr.univrouen.cv24.model.Diplome;
import fr.univrouen.cv24.model.TitreDiplome;
import fr.univrouen.cv24.repository.CVRepository;

@Controller
public class ResumeHtmlController {
	
	@Autowired
    private CVRepository cvRepository;
	
	@GetMapping("/cv/resume/html")	
    public String getResumeAsHtml(Model model) {
        List<CV> cvs = cvRepository.findAll();
        cvs.forEach(cv -> {
            Collections.sort(cv.getDiplomes(), Comparator.comparing(Diplome::getDateObtention).reversed());
        });
        model.addAttribute("cvs", cvs);
        return "resume";
           
    }
}
