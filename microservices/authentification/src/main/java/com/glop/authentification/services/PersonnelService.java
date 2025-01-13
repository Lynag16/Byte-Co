package com.glop.authentification.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glop.authentification.entities.Personnel;

import com.glop.authentification.dto.PersonnelDTO;
import com.glop.authentification.mappers.PersonnelMapper;

import com.glop.authentification.repositories.PersonnelRepository;

@Service
public class PersonnelService {

    @Autowired
    private PersonnelRepository personnelRepository;

    // Méthode pour enregistrer un nouveau Personnel
    public PersonnelDTO registerPersonnel(PersonnelDTO personnelDTO) {
        Personnel personnel = PersonnelMapper.toEntity(personnelDTO); // Convertir le DTO en entité
        Personnel savedPersonnel = personnelRepository.save(personnel); // Sauvegarder en base
        return PersonnelMapper.toDTO(savedPersonnel); // Retourner le DTO
    }

    // Méthode pour authentifier un Personnel et retourner le PersonnelDTO en cas de succès
    public PersonnelDTO authenticatePersonnelAndGetPersonnel(String email, String motdepasse) {
        Personnel personnel = personnelRepository.findByEmailpersonnel(email);
        if (personnel != null && motdepasse.equals(personnel.getmotdepassepersonnel())) {
            return PersonnelMapper.toDTO(personnel); // Retourner le DTO si l'authentification réussie
        }
        return null; // Retourner null si l'authentification échoue
    }
    
    // Méthode pour réinitialiser le mot de passe d'un Personnel
    public boolean resetPassword(String email, String newPassword) {
        Personnel personnel = personnelRepository.findByEmailpersonnel(email);
        if (personnel != null) {
            personnel.setmotdepassepersonnel(newPassword); // Met à jour le mot de passe
            personnelRepository.save(personnel); // Sauvegarde le Personnel avec le nouveau mot de passe

            return true;
        }
        return false;
    }
}
