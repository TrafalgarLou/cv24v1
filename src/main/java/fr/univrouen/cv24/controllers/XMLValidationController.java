package fr.univrouen.cv24.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

@RestController
public class XMLValidationController {

    @PostMapping("/validate-xml")
    public ResponseEntity<String> validateXML(@RequestBody String xmlContent) {
        try {
            // Charger le schéma XSD            
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream xsdStream = classLoader.getResourceAsStream("cv.xsd");
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsdStream));


            // Créer un validateur XML avec le schéma
            Validator validator = schema.newValidator();

            // Valider le document XML
            validator.validate(new StreamSource(new StringReader(xmlContent)));

            return ResponseEntity.ok("Le document XML est valide par rapport au schéma XSD.");
        } catch (SAXException e) {
            // Erreur de validation XML
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur de validation XML : " + e.getMessage());
        } catch (IOException e) {
            // Erreur de lecture du schéma XSD
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur de lecture du schéma XSD : " + e.getMessage());
        }
    }
}
