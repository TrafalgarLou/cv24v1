package fr.univrouen.cv24.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.univrouen.cv24.model.CV;

public interface CVRepository extends JpaRepository<CV,Long>{	

	Optional<CV> findByNomAndPrenomAndTel(String nom, String prenom, String tel);

}
