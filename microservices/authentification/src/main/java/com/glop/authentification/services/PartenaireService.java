package com.glop.authentification.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glop.authentification.entities.Partenaire;
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
    public boolean authenticatepartenaire(String emailPartenaire, String motdepassePartenaire) {
        Partenaire partenaire = partenaireRepository.findByEmailPartenaire(emailPartenaire);
        if (partenaire != null && motdepassePartenaire.equals(partenaire.getMotdepassePartenaire())) {
            return true; // Connexion réussie
        }
        return false; // Échec de la connexion
    }
}
