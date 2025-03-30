package com.glop.gestionsinistres.repository;

import com.glop.gestionsinistres.model.sinistre.AffectationSinistre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AffectationSinistreRepository extends JpaRepository<AffectationSinistre, Long> {
    List<AffectationSinistre> findByPartenaireId(String partenaireId);
    List<AffectationSinistre> findBySinistreId(Long sinistreId);
}
