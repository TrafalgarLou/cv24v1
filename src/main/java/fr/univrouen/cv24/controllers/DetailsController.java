package fr.univrouen.cv24.controllers;

import java.io.StringWriter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.univrouen.cv24.model.CV;
import fr.univrouen.cv24.model.Certification;
import fr.univrouen.cv24.model.Competence;
import fr.univrouen.cv24.model.Diplome;
import fr.univrouen.cv24.model.ExperienceProfessionnelle;
import fr.univrouen.cv24.model.InformationComplementaire;
import fr.univrouen.cv24.model.TitreDiplome;
import fr.univrouen.cv24.model.parser.AutreType;
import fr.univrouen.cv24.model.parser.CertifType;
import fr.univrouen.cv24.model.parser.CertificationType;
import fr.univrouen.cv24.model.parser.CompType;
import fr.univrouen.cv24.model.parser.Cv24Type;
import fr.univrouen.cv24.model.parser.DetailType;
import fr.univrouen.cv24.model.parser.DipType;
import fr.univrouen.cv24.model.parser.DiversType;
import fr.univrouen.cv24.model.parser.GenreType;
import fr.univrouen.cv24.model.parser.IdentiteType;
import fr.univrouen.cv24.model.parser.LvType;
import fr.univrouen.cv24.model.parser.NivsType;
import fr.univrouen.cv24.model.parser.ObjType;
import fr.univrouen.cv24.model.parser.ProfType;
import fr.univrouen.cv24.model.parser.StatusType;
import fr.univrouen.cv24.repository.CVRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

@RestController
public class DetailsController {
	@Autowired
	private CVRepository cvRepository;
	
	@GetMapping("/cv24/xml")
	public String getDetailsOfCVXML(
			@RequestParam(value = "id") int id) {
			Optional<CV> optionalCV = cvRepository.findById((long) id);
			if(optionalCV.isPresent()) {
				Cv24Type cvMarshallingObject = cvtoCV24Type(optionalCV.get());
				try {
					JAXBContext jaxbContext = JAXBContext.newInstance(Cv24Type.class);
					Marshaller marshaller = jaxbContext.createMarshaller();
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
					StringWriter stringWriter = new StringWriter();
					marshaller.marshal(cvMarshallingObject, stringWriter);
					return stringWriter.toString();
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}									
			}
		
		return "pas trouvé";
		
	}
	
	
	private Cv24Type cvtoCV24Type(CV myCv) {
		Cv24Type result = new Cv24Type();
		IdentiteType identite = new IdentiteType();
		identite.setGenre(GenreType.valueOf(myCv.getGenre()));
		identite.setNom(myCv.getNom());
		identite.setPrenom(myCv.getPrenom());
		
		if (myCv.getMel() != null)  identite.setMel(myCv.getMel());
		if (myCv.getTel() != null) identite.setTel(myCv.getTel());
		result.setIdentite(identite);
		ObjType obj = new ObjType();
		obj.setObjectif(myCv.getObjectif());
		obj.setStatus(StatusType.fromValue(myCv.getStatus()));
		result.setObjectif(obj);
		ProfType  prof = new ProfType();
		for(ExperienceProfessionnelle exp : myCv.getExperiencesProfessionnelles()) {
			DetailType detail = new DetailType();
			detail.setTitre(exp.getTitre());
			if(dateToXMLGregorianCalendar(exp.getDateDebut()) != null) {
				detail.setDatedeb(dateToXMLGregorianCalendar(exp.getDateDebut()));
			}
			if(exp.getDateFin() != null && dateToXMLGregorianCalendar(exp.getDateFin()) != null) {
				detail.setDatefin(dateToXMLGregorianCalendar(exp.getDateFin()));
			}
			prof.getDetail().add(detail);
		}
		result.setProf(prof);
		
		CompType competence = new CompType();
		for(Diplome d : myCv.getDiplomes()) {
			DipType dip = new DipType();
			if(d.getInstitut() != null) dip.setInstitut(d.getInstitut());
			dip.setDate(dateToXMLGregorianCalendar(d.getDateObtention()));
			for(TitreDiplome td : d.getTitre()) {
				dip.getTitre().add(td.getTitre());
			}
			dip.setNiveau(d.getNiveau());
			competence.getDiplome().add(dip);
		}
		if(myCv.getCertifications() != null) {			
			for(Certification c : myCv.getCertifications()) {
				CertifType cert = new CertifType();
				cert.setTitre(c.getTitre());
				cert.setDatedeb(dateToXMLGregorianCalendar(c.getDateDebut()));
				if(c.getDateFin() != null) cert.setDatefin(dateToXMLGregorianCalendar(c.getDateFin()));				
				competence.getCertif().add(cert);
			}			
		}
		result.setCompetences(competence);
		
		DiversType dt = new DiversType();
		if(myCv.getCompetences() != null) {
			for(Competence c : myCv.getCompetences()) {
				LvType lv = new LvType();
				lv.setCert(CertificationType.valueOf(c.getCert()));
				lv.setLang(c.getLang());
				if(c.getNivi() != null) lv.setNivi(Integer.valueOf(c.getNivi()));
				if(c.getNivs() != null) lv.setNivs(NivsType.fromValue(c.getNivs()));
				dt.getLv().add(lv);
			}
		}
		if(myCv.getInformationsComplementaires() != null) {
			for(InformationComplementaire ic : myCv.getInformationsComplementaires()) {
				AutreType at = new AutreType();
				at.setTitre(ic.getTitre());
				at.setComment(ic.getCommentaire());
				dt.getAutre().add(at);
			}
		}
		
		result.setDivers(dt);
		
		return result;
		
	}
	
	private XMLGregorianCalendar dateToXMLGregorianCalendar(Date d) {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(d);
		XMLGregorianCalendar result = null;
		try {
			result = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}
