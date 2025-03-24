package com.glop.authentification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;



import com.glop.authentification.entities.Partenaire;

public interface PartenaireRepository extends JpaRepository<Partenaire, Integer> {
	Partenaire findByEmailPartenaire(String emailPartenaire);// Méthode personnalisée pour récupérer un partenaire par email
}
