package fr.univrouen.cv24.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.univrouen.cv24.model.CV;

public interface CVRepository extends JpaRepository<CV,Long>{

}
