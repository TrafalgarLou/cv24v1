package fr.univrouen.cv24.controllers;

import java.io.File;
import java.io.StringWriter;
import java.util.Comparator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.Source;


import fr.univrouen.cv24.model.CV;
import fr.univrouen.cv24.model.Diplome;
import fr.univrouen.cv24.model.TestCV;
import fr.univrouen.cv24.model.TitreDiplome;
import fr.univrouen.cv24.repository.CVRepository;
import util.*;
@RestController
public class GetController {
		
	
	@Autowired
    private CVRepository cvRepository;
	
	@GetMapping("/cv/resume/xml")
    public ResponseEntity<String> getResumeAsXml() {
        try {
            List<CV> cvs = cvRepository.findAll();
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Création du document XML
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("cvs");
            doc.appendChild(rootElement);

            for (CV cv : cvs) {
                Element cvElement = doc.createElement("cv");
                rootElement.appendChild(cvElement);

                // Id
                Element idElement = doc.createElement("id");
                idElement.appendChild(doc.createTextNode(String.valueOf(cv.getId())));
                cvElement.appendChild(idElement);

                // Identité
                Element identityElement = doc.createElement("identité");
                Element genreElement = doc.createElement("genre");
                Element nomElement = doc.createElement("nom");
                Element prenomElement = doc.createElement("nom");
                genreElement.appendChild(doc.createTextNode(cv.getGenre()));
                nomElement.appendChild(doc.createTextNode(cv.getNom()));
                prenomElement.appendChild(doc.createTextNode(cv.getPrenom()));
                identityElement.appendChild(genreElement);
                identityElement.appendChild(nomElement);
                identityElement.appendChild(prenomElement);
                cvElement.appendChild(identityElement);

                // Objectif avec statut
                Element objectiveElement = doc.createElement("objectif");                
                objectiveElement.appendChild(doc.createTextNode(
                        cv.getObjectif()));
                objectiveElement.setAttribute("status", cv.getStatus());
                cvElement.appendChild(objectiveElement);

                // Diplôme le plus élevé ou le plus récent
                Diplome highestDiploma = getHighestDiploma(cv);
                if (highestDiploma != null) {
                    Element diplomaElement = doc.createElement("diplome");
                    StringBuilder titreBuilder = new StringBuilder();
                    
                    for(TitreDiplome td : highestDiploma.getTitre()) {
                    	titreBuilder.append(td.getTitre()+" ");                    	
                    }
                    diplomaElement.appendChild(doc.createTextNode(titreBuilder.toString()));
                    cvElement.appendChild(diplomaElement);
                }
            }

         // Transformer le document XML en chaîne de caractères
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // Configuration de l'indentation
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5"); 

            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String xmlString = writer.getBuffer().toString();

            // Retourner la réponse
            return ResponseEntity.ok(xmlString);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la génération du XML.");
        }
    }
	/*
	@GetMapping("/cv/resume/html")	
    public ResponseEntity<String> getResumeAsHtml() {
        try {
            List<CV> cvs = cvRepository.findAll();
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Création du document XML
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("cvs");
            doc.appendChild(rootElement);

            for (CV cv : cvs) {
                Element cvElement = doc.createElement("cv");
                rootElement.appendChild(cvElement);

                // Id
                Element idElement = doc.createElement("id");
                idElement.appendChild(doc.createTextNode(String.valueOf(cv.getId())));
                cvElement.appendChild(idElement);

                // Identité
                Element identityElement = doc.createElement("identité");
                Element genreElement = doc.createElement("genre");
                Element nomElement = doc.createElement("nom");
                Element prenomElement = doc.createElement("prenom");
                genreElement.appendChild(doc.createTextNode(cv.getGenre()));
                nomElement.appendChild(doc.createTextNode(cv.getNom()));
                prenomElement.appendChild(doc.createTextNode(cv.getPrenom()));
                identityElement.appendChild(genreElement);
                identityElement.appendChild(nomElement);
                identityElement.appendChild(prenomElement);
                cvElement.appendChild(identityElement);

                // Objectif avec statut
                Element objectiveElement = doc.createElement("objectif");                
                objectiveElement.appendChild(doc.createTextNode(
                        cv.getObjectif()));
                objectiveElement.setAttribute("status", cv.getStatus());
                cvElement.appendChild(objectiveElement);

                // Diplôme le plus élevé ou le plus récent
                Diplome highestDiploma = getHighestDiploma(cv);
                if (highestDiploma != null) {
                    Element diplomaElement = doc.createElement("diplome");
                    StringBuilder titreBuilder = new StringBuilder();
                    
                    for(TitreDiplome td : highestDiploma.getTitre()) {
                    	titreBuilder.append(td.getTitre()+" ");                    	
                    }
                    diplomaElement.appendChild(doc.createTextNode(titreBuilder.toString()));
                    cvElement.appendChild(diplomaElement);
                }
            }

         // Transformer le document XML en chaîne de caractères
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

         // Configuration de l'indentation
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");

            // Chargement du fichier XSLT
            ClassLoader classLoader = getClass().getClassLoader();
            Source xsltSource = new StreamSource(classLoader.getResourceAsStream("resume.xslt"));

            // Création du transformer avec le fichier XSLT
            Transformer xsltTransformer = transformerFactory.newTransformer(xsltSource);

            // Transformation du XML en HTML en utilisant le transformer avec le fichier XSLT
            StringWriter htmlWriter = new StringWriter();
            xsltTransformer.transform(new DOMSource(doc), new StreamResult(htmlWriter));

            // Retourner la réponse HTML
            return ResponseEntity.ok(htmlWriter.toString());       

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    */

    // Méthode pour obtenir le diplôme le plus élevé ou le plus récent
    private Diplome getHighestDiploma(CV cv) {
    	List<Diplome> diplomes = cv.getDiplomes();

        // Si la liste de diplômes est vide, retourner null
        if (diplomes.isEmpty()) {
            return null;
        }

        // Trier les diplômes par niveau ou par date d'obtention
        diplomes.sort(Comparator.comparing(Diplome::getNiveau).reversed());

        // Retourner le premier diplôme de la liste triée
        return diplomes.get(0);
    }
}
