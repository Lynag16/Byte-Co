package com.glop.gestionsinistres.repository;

import com.glop.gestionsinistres.model.Sinistre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SinistreRepository extends JpaRepository<Sinistre, Long> {

    // Obtenir tous les sinistres d’un utilisateur donné
    List<Sinistre> findByUserId(String userId);

    // Pour filtrer par statut ou type
    List<Sinistre> findByStatut(String statut);
    List<Sinistre> findByType(String type);
}