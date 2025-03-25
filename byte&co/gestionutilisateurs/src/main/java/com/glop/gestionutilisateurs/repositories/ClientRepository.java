package com.glop.gestionutilisateurs.repositories;

import com.glop.gestionutilisateurs.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
