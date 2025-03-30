package com.glop.authentification.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.glop.authentification.entities.Personnel;

import com.glop.authentification.dto.PersonnelDTO;
import com.glop.authentification.mappers.PersonnelMapper;

import com.glop.authentification.repositories.PersonnelRepository;

@Service
public class PersonnelService {

    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public PersonnelDTO registerGestionnaire(PersonnelDTO personnelDTO) {
        Personnel personnel = new Personnel();

        // Mapping du PersonnelDTO vers Personnel
        personnel.setNompersonnel(personnelDTO.getNompersonnel());
        personnel.setPrenompersonnel(personnelDTO.getPrenompersonnel());
        personnel.setEmailpersonnel(personnelDTO.getEmailpersonnel());
        personnel.setTelephonepersonnel(personnelDTO.getTelephonepersonnel());
        personnel.setDepartementpersonnel(personnelDTO.getDepartementpersonnel());
        personnel.setAdressepersonnel(personnelDTO.getAdressepersonnel());
        personnel.setMotdepassepersonnel(passwordEncoder.encode(personnelDTO.setMotdepassepersonnel())); // mot de passe crypté
        personnel.setRolepersonnel("GESTIONNAIRE");

        // Sauvegarder dans la base de données
        Personnel savedPersonnel = personnelRepository.save(personnel);

        return PersonnelMapper.toDTO(savedPersonnel);  // Retourner le DTO du personnel créé
    }

    

    // Méthode pour authentifier un Personnel et retourner le PersonnelDTO en cas de succès
    public PersonnelDTO authenticatePersonnelAndGetPersonnel(String email, String motdepasse) {
        Personnel personnel = personnelRepository.findByEmailpersonnel(email);
        if (personnel != null && passwordEncoder.matches(motdepasse, personnel.setMotdepassepersonnel())) {
            return PersonnelMapper.toDTO(personnel);
        }
        return null;
    }

    // Méthode pour réinitialiser le mot de passe d'un Personnel
    public boolean resetPassword(String email, String newPassword) {
        Personnel personnel = personnelRepository.findByEmailpersonnel(email);
        if (personnel != null) {
            personnel.setMotdepassepersonnel(newPassword);
            personnelRepository.save(personnel);

            return true;
        }
        return false;
    }

    public PersonnelDTO findByEmailpersonnel(String emailpersonnel) {
        Personnel personnel = personnelRepository.findByEmailpersonnel(emailpersonnel);
        if (personnel != null) {
            return PersonnelMapper.toDTO(personnel);  // Convertir l'entité Personnel en PersonnelDTO
        }
        return null;
    }
}
