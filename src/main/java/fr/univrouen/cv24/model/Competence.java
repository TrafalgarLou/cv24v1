package fr.univrouen.cv24.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cv_id")
    private CV cv;
    
    private String nivi;
    private String nivs;
    private String cert;
    private String lang;    
    
    // Constructeurs
    public Competence() {    	 
    }

    public Competence(String nivi, String nivs, String cert, String lang) {
        this.nivi = nivi;
        this.nivs = nivs;
        this.cert = cert;
        this.lang = lang;        
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CV getCv() {
        return cv;
    }

    public void setCv(CV cv) {
        this.cv = cv;
    }

    public String getNivi() {
        return nivi;
    }

    public void setNivi(String nivi) {
        this.nivi = nivi;
    }

    public String getNivs() {
        return nivs;
    }

    public void setNivs(String nivs) {
        this.nivs = nivs;
    }

    public String getCert() {
        return cert;
    }

    public void setCert(String cert) {
        this.cert = cert;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
