package com.glop.gestionsinistres.repository;

import com.glop.gestionsinistres.model.sinistre.Sinistre;
import com.glop.gestionsinistres.model.sinistre.StatutSinistre;
import com.glop.gestionsinistres.model.sinistre.TypeSinistre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SinistreRepository extends JpaRepository<Sinistre, Long> {

    List<Sinistre> findByStatut(StatutSinistre statut);
    List<Sinistre> findByUserId(String userId);
    List<Sinistre> findByTypeAndStatut(TypeSinistre type, StatutSinistre statut);
    List<Sinistre> findByType(TypeSinistre type);
}
