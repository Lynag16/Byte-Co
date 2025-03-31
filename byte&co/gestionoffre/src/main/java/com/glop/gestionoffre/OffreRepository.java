package com.glop.gestionoffre;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glop.gestionoffre.Offre;

@Repository
public interface OffreRepository extends JpaRepository<Offre, Long> {
}
