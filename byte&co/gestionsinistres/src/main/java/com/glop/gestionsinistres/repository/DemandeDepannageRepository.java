package com.glop.gestionsinistres.repository;

import com.glop.gestionsinistres.model.assistance.DemandeDepannage;
import com.glop.gestionsinistres.model.assistance.StatutDemande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandeDepannageRepository extends JpaRepository<DemandeDepannage, Long> {
    List<DemandeDepannage> findByUserId(String userId);
    List<DemandeDepannage> findByStatut(StatutDemande statut);
}
