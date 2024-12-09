package com.glop.authentification.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glop.authentification.entities.Partenaire;
import com.glop.authentification.entities.Personnel;
import com.glop.authentification.repositories.PartenaireRepository;

@Service
public class PartenaireService {

    @Autowired
    private PartenaireRepository partenaireRepository;

    // Méthode pour enregistrer un nouveau partenaire
    public Partenaire registerpartenaire(Partenaire partenaire) {
        return partenaireRepository.save(partenaire); // Enregistrement sans hashage du mot de passe
    }

    
    // Méthode pour authentifier un partenaire en fonction de l'email et du mot de passe
    public Partenaire authenticatePartenaireAndGetPartenaire(String emailPartenaire, String motdepassePartenaire) {
        Partenaire partenaire = partenaireRepository.findByEmailPartenaire(emailPartenaire);
        if (partenaire != null && motdepassePartenaire.equals(partenaire.getMotdepassePartenaire())) {
            return partenaire; // Retourne l'objet Partenaire si l'authentification réussie
        }
        return null; // Retourne null si l'authentification échoue
    }
    
    // Méthode pour réinitialiser le mot de passe d'un client
    public boolean resetPassword(String email, String newPassword) {
    	Partenaire partenaire = partenaireRepository.findByEmailPartenaire(email);
        if (partenaire != null) {
        	partenaire.setMotdepassePartenaire(newPassword); // Met à jour le mot de passe
        	partenaireRepository.save(partenaire); // Sauvegarde le client avec le nouveau mot de passe
            return true;
        }
        return false;
    }
    
}
