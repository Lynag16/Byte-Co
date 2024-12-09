package com.glop.authentification.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glop.authentification.entities.Personnel;
import com.glop.authentification.repositories.PersonnelRepository;

@Service
public class PersonnelService {

    @Autowired
    private PersonnelRepository personnelRepository;

    // Méthode pour enregistrer un nouveau Personnel
    public Personnel registerPersonnel(Personnel personnel) {
        return personnelRepository.save(personnel);
    }


    // Méthode pour authentifier un Personnel et retourner l'objet Personnel en cas de succès
    public Personnel authenticatePersonnelAndGetPersonnel(String email, String motdepasse) {
    	Personnel personnel = personnelRepository.findByEmailpersonnel(email);
        if (personnel != null && motdepasse.equals(personnel.getmotdepassepersonnel())) {
            return personnel; // Retourne l'objet Client si l'authentification réussie
        }
        return null; // Retourne null si l'authentification échoue
    }
    
    // Méthode pour réinitialiser le mot de passe d'un client
    public boolean resetPassword(String email, String newPassword) {
    	Personnel personnel = personnelRepository.findByEmailpersonnel(email);
        if (personnel != null) {
        	personnel.setmotdepassepersonnel(newPassword); // Met à jour le mot de passe
        	personnelRepository.save(personnel); // Sauvegarde le client avec le nouveau mot de passe
            return true;
        }
        return false;
    }
}
