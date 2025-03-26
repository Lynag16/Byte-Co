package com.glop.gestionsinistres.repository;

import com.glop.gestionsinistres.model.Sinistre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SinistreRepository extends JpaRepository<Sinistre, Long> {
}
