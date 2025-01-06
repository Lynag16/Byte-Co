package com.glop.authentification.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glop.authentification.entities.Partenaire;
import com.glop.authentification.dto.PartenaireDTO;
import com.glop.authentification.mappers.PartenaireMapper;
import com.glop.authentification.repositories.PartenaireRepository;

@Service
public class PartenaireService {

    @Autowired
    private PartenaireRepository partenaireRepository;

    // Méthode pour enregistrer un nouveau partenaire
    public PartenaireDTO registerPartenaire(Partenaire partenaire) {
        // Enregistrement du partenaire dans la base de données
        Partenaire savedPartenaire = partenaireRepository.save(partenaire);
        // Conversion de l'entité en DTO avant de la renvoyer
        return PartenaireMapper.toDTO(savedPartenaire);
    }

    // Méthode pour authentifier un partenaire en fonction de l'email et du mot de passe
    public PartenaireDTO authenticatePartenaireAndGetPartenaire(String emailPartenaire, String motdepassePartenaire) {
        // Recherche du partenaire par email
        Partenaire partenaire = partenaireRepository.findByEmailPartenaire(emailPartenaire);
        if (partenaire != null && motdepassePartenaire.equals(partenaire.getMotdepassePartenaire())) {
            // Si l'authentification réussit, conversion de l'entité en DTO
            return PartenaireMapper.toDTO(partenaire);
        }
        return null; // Retourne null si l'authentification échoue
    }

    // Méthode pour réinitialiser le mot de passe d'un partenaire
    public boolean resetPassword(String email, String newPassword) {
        Partenaire partenaire = partenaireRepository.findByEmailPartenaire(email);
        if (partenaire != null) {
            partenaire.setMotdepassePartenaire(newPassword); // Mise à jour du mot de passe
            partenaireRepository.save(partenaire); // Sauvegarde du partenaire avec le nouveau mot de passe
            return true;
        }
        return false;
    }
}
