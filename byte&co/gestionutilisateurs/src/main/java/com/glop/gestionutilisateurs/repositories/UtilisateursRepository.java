package com.glop.gestionutilisateurs.repositories;

import com.glop.gestionutilisateurs.entities.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateursRepository extends JpaRepository<Utilisateurs, Long> {
}