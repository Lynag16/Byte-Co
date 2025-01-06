package com.glop.authentification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glop.authentification.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	Client findByEmail(String email);// Méthode personnalisée pour récupérer un client par email
	boolean existsByEmail(String email); // New method to check if client exists by email

}

