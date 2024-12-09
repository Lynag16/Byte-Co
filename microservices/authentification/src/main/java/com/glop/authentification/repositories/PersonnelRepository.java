package com.glop.authentification.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glop.authentification.entities.Personnel;

@Repository
	public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
	Personnel findByEmailpersonnel(String emailpersonnel);
	}

	
