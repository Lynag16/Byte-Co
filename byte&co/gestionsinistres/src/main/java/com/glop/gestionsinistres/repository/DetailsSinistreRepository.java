package com.glop.gestionsinistres.repository;

import com.glop.gestionsinistres.model.DetailsSinistre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsSinistreRepository extends JpaRepository<DetailsSinistre, Long> {

    DetailsSinistre findBySinistreId(Long sinistreId);
}
