package com.glop.gestionsinistres.repository;

import com.glop.gestionsinistres.model.Sinistre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SinistreRepository extends JpaRepository<Sinistre, Long> {
}
