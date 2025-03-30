package com.glop.gestionutilisateurs.repositories;

import com.glop.gestionutilisateurs.entities.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    Personnel findByEmailpersonnel(String emailpersonnel);
    Personnel findByIdpersonnel(Long idpersonnel);
}