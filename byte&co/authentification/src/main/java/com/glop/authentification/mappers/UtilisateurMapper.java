package com.glop.authentification.mappers;

import com.glop.authentification.dto.UtilisateurDTO;
import com.glop.authentification.entities.Utilisateur;

public class UtilisateurMapper {

    // Convertir l'entité Utilisateur en UtilisateurDTO
    public static UtilisateurDTO toDTO(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setId(utilisateur.getId());
        utilisateurDTO.setEmail(utilisateur.getEmail());
        utilisateurDTO.setMotDePasse(utilisateur.getMotDePasse());
        utilisateurDTO.setTypeUtilisateur(utilisateur.getTypeUtilisateur());
        utilisateurDTO.setNom(utilisateur.getNom());
        utilisateurDTO.setPrenom(utilisateur.getPrenom());
        utilisateurDTO.setDateNaissance(utilisateur.getDateNaissance());
        utilisateurDTO.setCodePostal(utilisateur.getCodePostal());
        return utilisateurDTO;
    }

    // Convertir l'UtilisateurDTO en entité Utilisateur
    public static Utilisateur toEntity(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDTO.getId());
        utilisateur.setEmail(utilisateurDTO.getEmail());
        utilisateur.setMotDePasse(utilisateurDTO.getMotDePasse());
        utilisateur.setTypeUtilisateur(utilisateurDTO.getTypeUtilisateur());
        utilisateur.setNom(utilisateurDTO.getNom());
        utilisateur.setPrenom(utilisateurDTO.getPrenom());
        utilisateur.setDateNaissance(utilisateurDTO.getDateNaissance());
        utilisateur.setCodePostal(utilisateurDTO.getCodePostal());
        return utilisateur;
    }
    
}
