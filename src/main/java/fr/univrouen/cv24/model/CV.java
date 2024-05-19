package fr.univrouen.cv24.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"genre", "nom", "prenom", "tel"})
	})
public class CV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String genre;
    private String nom;
    private String prenom;
    private String tel;
    private String mel;
    private String objectif;
    private String status;
    
    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExperienceProfessionnelle> experiencesProfessionnelles;

    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Competence> competences;

    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diplome> diplomes;

    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Certification> certifications;

    @OneToMany(mappedBy = "cv", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InformationComplementaire> informationsComplementaires;    
    
    // Constructeurs
    public CV() {
    	experiencesProfessionnelles = new ArrayList<ExperienceProfessionnelle>();
    	competences = new ArrayList<Competence>();
    	diplomes = new ArrayList<Diplome>();
    	certifications = new ArrayList<Certification>();
    	informationsComplementaires = new ArrayList<InformationComplementaire>();
    }

    // Getters et setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMel() {
        return mel;
    }

    public void setMel(String mel) {
        this.mel = mel;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public List<ExperienceProfessionnelle> getExperiencesProfessionnelles() {
        return experiencesProfessionnelles;
    }

    public void setExperiencesProfessionnelles(List<ExperienceProfessionnelle> experiencesProfessionnelles) {
        this.experiencesProfessionnelles = experiencesProfessionnelles;
    }

    public List<Competence> getCompetences() {
        return competences;
    }

    public void setCompetences(List<Competence> competences) {
        this.competences = competences;
    }

    public List<Diplome> getDiplomes() {
        return diplomes;
    }

    public void setDiplomes(List<Diplome> diplomes) {
        this.diplomes = diplomes;
    }

    public List<Certification> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<Certification> certifications) {
        this.certifications = certifications;
    }

    public List<InformationComplementaire> getInformationsComplementaires() {
        return informationsComplementaires;
    }

    public void setInformationsComplementaires(List<InformationComplementaire> informationsComplementaires) {
        this.informationsComplementaires = informationsComplementaires;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
