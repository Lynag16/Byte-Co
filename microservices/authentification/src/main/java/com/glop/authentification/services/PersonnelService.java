package com.glop.authentification.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glop.authentification.entities.Personnel;
import com.glop.authentification.repositories.PersonnelRepository;

@Service
public class PersonnelService {

    @Autowired
    private PersonnelRepository personnelRepository;

    // Méthode pour enregistrer un nouveau personnel
    public Personnel registerPersonnel(Personnel personnel) throws Exception {
        // Vérifie si le personnel avec cet email existe déjà
        if (personnelRepository.findByEmailpersonnel(personnel.getEmailpersonnel()).isPresent()) {
            throw new Exception("Un personnel avec cet email existe déjà.");
        }

        return personnelRepository.save(personnel);
    }

    // Méthode pour authentifier un personnel
    public boolean authenticatePersonnel(String email, String motdepassepersonnel) {
        Personnel personnel = personnelRepository.findByEmailpersonnel(email).orElse(null);

        if (personnel != null && personnel.getmotdepassepersonnel().equals(motdepassepersonnel)) {
            return true;
        }

        return false;
    }
}
