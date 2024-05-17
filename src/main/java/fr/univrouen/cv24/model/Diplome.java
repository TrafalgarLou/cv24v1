package fr.univrouen.cv24.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Diplome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cv_id")
    private CV cv;

    private int niveau;
    private Date dateObtention;
    private String institut;
    
    @OneToMany(mappedBy = "diplome", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TitreDiplome> titres;

    // Constructeurs
    public Diplome() {
    }

    public Diplome(int niveau, Date dateObtention, String institut) {
        this.niveau = niveau;
        this.dateObtention = dateObtention;
        this.institut = institut;      
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

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public Date getDateObtention() {
        return dateObtention;
    }

    public void setDateObtention(Date dateObtention) {
        this.dateObtention = dateObtention;
    }

    public String getInstitut() {
        return institut;
    }

    public void setInstitut(String institut) {
        this.institut = institut;
    }

    public List<TitreDiplome> getTitre() {
        return titres;
    }

    public void setTitre(List<TitreDiplome> titre) {
        this.titres = titre;
    }
    
    public void addTitre(TitreDiplome titre) {
    	this.titres.add(titre);
    }
}
