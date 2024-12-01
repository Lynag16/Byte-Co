package com.glop.authentification.services;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glop.authentification.entities.Partenaire;
import com.glop.authentification.repositories.PartenaireRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class PartenaireService {

    @Autowired
    private PartenaireRepository partenaireRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; 

    // Méthode pour enregistrer un nouveau partenaire
    public Partenaire registerpartenaire(Partenaire partenaire) {
        // Hashage du mot de passe
        partenaire.setMotdepassePartenaire(passwordEncoder.encode(partenaire.getMotdepassePartenaire()));
        return partenaireRepository.save(partenaire);
    }

    // Méthode pour authentifier un partenaire en fonction de l'email et du mot de passe
    public boolean authenticatepartenaire(String emailPartenaire, String motdepassePartenaire) {
        Partenaire partenaire = partenaireRepository.findByEmailPartenaire(emailPartenaire);
        if (partenaire != null && passwordEncoder.matches(motdepassePartenaire, partenaire.getMotdepassePartenaire())) {
            return true; // Connexion réussie
        }
        return false; // Échec de la connexion
    }
}
