package fr.univrouen.cv24.controllers;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univrouen.cv24.model.AutreType;
import fr.univrouen.cv24.model.CV;
import fr.univrouen.cv24.model.CertifType;
import fr.univrouen.cv24.model.Certification;
import fr.univrouen.cv24.model.CompType;
import fr.univrouen.cv24.model.Cv24Type;
import fr.univrouen.cv24.model.DetailType;
import fr.univrouen.cv24.model.DipType;
import fr.univrouen.cv24.model.Diplome;
import fr.univrouen.cv24.model.DiversType;
import fr.univrouen.cv24.model.ExperienceProfessionnelle;
import fr.univrouen.cv24.model.IdentiteType;
import fr.univrouen.cv24.model.LvType;
import fr.univrouen.cv24.model.ObjType;
import fr.univrouen.cv24.model.ProfType;
import fr.univrouen.cv24.model.TitreDiplome;
import fr.univrouen.cv24.repository.CVRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

@RestController
public class PostController {
		@RequestMapping(value = "/testPost", method = RequestMethod.POST,
				consumes = "application/xml")
		public String postTest(@RequestBody String flux) {
			return ("<result><response>Message reçu : </response>"
					+ flux + "</result>");
		}
		
		
		@Autowired
	    private CVRepository cvRepository;

	    @PostMapping("/insert")
	    public ResponseEntity<String> addCV(@RequestBody String flux) {
	        try {
	            JAXBContext context = JAXBContext.newInstance(Cv24Type.class);
	            Unmarshaller unmarshaller = context.createUnmarshaller();
	            Cv24Type cv24 = (Cv24Type) unmarshaller.unmarshal(new StringReader(flux));

	            CV newCV = new CV();
	            newCV.setGenre(cv24.getIdentite().getGenre().toString());
	            newCV.setPrenom(cv24.getIdentite().getNom());
	            newCV.setNom(cv24.getIdentite().getPrenom());
	            newCV.setTel(cv24.getIdentite().getTel());
	            newCV.setMel(cv24.getIdentite().getMel());
	            newCV.setObjectif(cv24.getObjectif().getObjectif());
	            newCV.setStatus(cv24.getObjectif().getStatus().value());

	            List<ExperienceProfessionnelle> experiences = new ArrayList<>();
	            for (DetailType exp : cv24.getProf().getDetail()) {
	                ExperienceProfessionnelle experience = new ExperienceProfessionnelle();
	                experience.setTitre(exp.getTitre());
	                experience.setDateDebut(exp.getDatedeb().toGregorianCalendar().getTime());
	                experience.setDateFin(exp.getDatefin().toGregorianCalendar().getTime());
	                experience.setCv(newCV);
	                experiences.add(experience);
	            }
	            newCV.setExperiencesProfessionnelles(experiences);

	            List<Diplome> diplomes = new ArrayList<>();
	            for (DipType dipl : cv24.getCompetences().getDiplome()) {
	                Diplome diplome = new Diplome();
	                diplome.setInstitut(dipl.getInstitut());
	                diplome.setDateObtention(dipl.getDate().toGregorianCalendar().getTime());
	                List<TitreDiplome> titres = new ArrayList<>();
	                for (String d : dipl.getTitre()) {
	                    TitreDiplome td = new TitreDiplome();
	                    td.setTitre(d);
	                    td.setDiplome(diplome);
	                    titres.add(td);
	                }
	                diplome.setTitre(titres);
	                diplome.setCv(newCV);
	                diplomes.add(diplome);
	            }
	            newCV.setDiplomes(diplomes);
	            
	            List<Certification> certifications = new ArrayList<Certification>();
	            for(CertifType certif : cv24.getCompetences().getCertif()) {
	            	
	            }

	            cvRepository.save(newCV);

	            return ResponseEntity.status(HttpStatus.OK).body("OK");
	        } catch (JAXBException e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	        }
	    }
		
		
		@PostMapping("/test-unmarshalli")
		public ResponseEntity<String> handleXml(@RequestBody String xmlData) {
		    try {
		        // Créer le contexte JAXB pour le package contenant vos classes générées
		        JAXBContext context = JAXBContext.newInstance(Cv24Type.class);

		        // Créer l'objet Unmarshaller
		        Unmarshaller unmarshaller = context.createUnmarshaller();

		        // Désérialiser le XML en objet Java
		        Cv24Type cv24 = (Cv24Type) unmarshaller.unmarshal(new StringReader(xmlData));

		        // Construire une chaîne de caractères représentant les informations de l'objet cv24
		        StringBuilder responseBuilder = new StringBuilder();
		        responseBuilder.append("Informations du CV :\n");

		        // Affichage des informations sur l'identité
		        IdentiteType identite = cv24.getIdentite();
		        responseBuilder.append("Genre: ").append(identite.getGenre()).append("\n");
		        responseBuilder.append("Nom: ").append(identite.getNom()).append("\n");
		        responseBuilder.append("Prénom: ").append(identite.getPrenom()).append("\n");
		        responseBuilder.append("Téléphone: ").append(identite.getTel()).append("\n");
		        responseBuilder.append("Email: ").append(identite.getMel()).append("\n\n");

		        // Affichage de l'objectif
		        ObjType objectif = cv24.getObjectif();
		        responseBuilder.append("Objectif: ").append(objectif.getObjectif()).append("\n\n");

		        // Affichage des informations sur le professionnel
		        ProfType prof = cv24.getProf();
		        List<DetailType> details = prof.getDetail();
		        responseBuilder.append("Expérience professionnelle:\n");
		        for (DetailType detail : details) {
		            responseBuilder.append("Date de début: ").append(detail.getDatedeb()).append("\n");
		            responseBuilder.append("Date de fin: ").append(detail.getDatefin()).append("\n");
		            responseBuilder.append("Titre: ").append(detail.getTitre()).append("\n");
		            responseBuilder.append("\n");
		        }

		        // Affichage des compétences
		        CompType competences = cv24.getCompetences();
		        responseBuilder.append("Compétences:\n");
		        // Supposons que vous voulez afficher seulement les diplômes
		        List<DipType> diplomes = competences.getDiplome();
		        for (DipType diplome : diplomes) {
		            responseBuilder.append("Diplôme: ").append(diplome.getTitre()).append("\n");
		            responseBuilder.append("Date: ").append(diplome.getDate()).append("\n");
		            responseBuilder.append("Institut: ").append(diplome.getInstitut()).append("\n");
		            responseBuilder.append("\n");
		        }

		        // Affichage des informations diverses
		        DiversType divers = cv24.getDivers();
		        if (divers != null) {
		            // Affichage des langues vivantes
		            responseBuilder.append("Langues vivantes:\n");
		            List<LvType> languesVivantes = divers.getLv();
		            for (LvType langue : languesVivantes) {
		                responseBuilder.append("Langue: ").append(langue.getLang()).append("\n");
		                responseBuilder.append("Certification: ").append(langue.getCert()).append("\n");
		                responseBuilder.append("Niveau scolaire: ").append(langue.getNivs()).append("\n");
		                responseBuilder.append("Niveau individuel: ").append(langue.getNivi()).append("\n");
		                responseBuilder.append("\n");
		            }

		            // Affichage des autres informations
		            responseBuilder.append("Autres informations:\n");
		            List<AutreType> autres = divers.getAutre();
		            for (AutreType autre : autres) {
		                responseBuilder.append("Titre: ").append(autre.getTitre()).append("\n");
		                responseBuilder.append("Commentaire: ").append(autre.getComment()).append("\n");
		                responseBuilder.append("\n");
		            }
		        }

		        // Répondre avec les informations du CV dans le corps de la réponse
		        return ResponseEntity.status(HttpStatus.OK).body(responseBuilder.toString());
		    } catch (JAXBException e) {
		        e.printStackTrace();
		        // En cas d'erreur de désérialisation, renvoyer un message d'erreur
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		    }
		}

}
