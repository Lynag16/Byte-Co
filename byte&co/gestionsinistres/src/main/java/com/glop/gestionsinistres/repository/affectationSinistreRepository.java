package com.glop.gestionsinistres.repository;

import com.glop.gestionsinistres.model.assistance.AffectationAssistance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface affectationSinistreRepository extends JpaRepository<AffectationAssistance, Long> {
}
