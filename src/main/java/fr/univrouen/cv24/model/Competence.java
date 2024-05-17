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

    private int niveauQualification;
    private String titre;
    
    // Constructeurs
    public Competence() {
    }

    public Competence(int niveauQualification, String titre) {
        this.niveauQualification = niveauQualification;
        this.titre = titre;
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

    public int getNiveauQualification() {
        return niveauQualification;
    }

    public void setNiveauQualification(int niveauQualification) {
        this.niveauQualification = niveauQualification;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
