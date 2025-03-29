package com.glop.gestionutilisateurs.repositories;

import com.glop.gestionutilisateurs.entities.Partenaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartenaireRepository extends JpaRepository<Partenaire, Integer> {

    Partenaire findByEmailPartenaire(String emailPartenaire);
    Partenaire findByNomPartenaire(String nomPartenaire);
    Partenaire findByIdPartenaire(int idPartenaire);


}
