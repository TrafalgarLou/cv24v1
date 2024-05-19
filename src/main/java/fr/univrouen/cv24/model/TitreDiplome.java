package fr.univrouen.cv24.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TitreDiplome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "diplome_id")
    private Diplome diplome;

    private String titre;
    
    public TitreDiplome() {
    	
    }

    
    public Diplome getDiplome() {
    	return this.diplome;
    }
    
    public void setDiplome(Diplome d) {
    	this.diplome = d;
    }
    
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	};
}
