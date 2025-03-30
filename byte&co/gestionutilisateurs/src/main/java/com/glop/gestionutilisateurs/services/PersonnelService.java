package com.glop.gestionutilisateurs.services;

import com.glop.gestionutilisateurs.dtos.PersonnelDTO;
import com.glop.gestionutilisateurs.entities.Personnel;
import com.glop.gestionutilisateurs.repositories.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonnelService {

    @Autowired
    private PersonnelRepository personnelRepository;

    // Récupérer tous les personnels
    public List<Personnel> getAllPersonnels() {
        return personnelRepository.findAll();
    }

    // Récupérer un personnel par son ID
    public Personnel getPersonnelById(Long id) {
        return personnelRepository.findById(id).orElse(null);
    }

    // Créer un nouveau personnel
    public Personnel createPersonnel(PersonnelDTO personnelDTO) {
        Personnel personnel = convertToEntity(personnelDTO);
        return personnelRepository.save(personnel);
    }

    // Mettre à jour un personnel
    public Personnel updatePersonnel(Long id, PersonnelDTO personnelDTO) {
        Optional<Personnel> existing = personnelRepository.findById(id);
        if (existing.isPresent()) {
            Personnel personnel = convertToEntity(personnelDTO);
            personnel.setIdpersonnel(id);
            return personnelRepository.save(personnel);
        }
        return null;
    }

    // Supprimer un personnel
    public void deletePersonnel(Long id) {
        personnelRepository.deleteById(id);
    }

    // Convertir PersonnelDTO en Personnel
    private Personnel convertToEntity(PersonnelDTO personnelDTO) {
        Personnel personnel = new Personnel();
        personnel.setNompersonnel(personnelDTO.getNompersonnel());
        personnel.setPrenompersonnel(personnelDTO.getPrenompersonnel());
        personnel.setEmailpersonnel(personnelDTO.getEmailpersonnel());
        personnel.setTelephonepersonnel(personnelDTO.getTelephonepersonnel());
        personnel.setRolepersonnel(personnelDTO.getRolepersonnel());
        personnel.setDepartementpersonnel(personnelDTO.getDepartementpersonnel());
        personnel.setAdressepersonnel(personnelDTO.getAdressepersonnel());
        personnel.setMotdepassepersonnel(personnelDTO.getMotdepassepersonnel());
        return personnel;
    }

    // Convertir Personnel en PersonnelDTO
    private PersonnelDTO convertToDTO(Personnel personnel) {
        PersonnelDTO personnelDTO = new PersonnelDTO();
        personnelDTO.setIdpersonnel(personnel.getIdpersonnel());
        personnelDTO.setNompersonnel(personnel.getNompersonnel());
        personnelDTO.setPrenompersonnel(personnel.getPrenompersonnel());
        personnelDTO.setEmailpersonnel(personnel.getEmailpersonnel());
        personnelDTO.setTelephonepersonnel(personnel.getTelephonepersonnel());
        personnelDTO.setRolepersonnel(personnel.getRolepersonnel());
        personnelDTO.setDepartementpersonnel(personnel.getDepartementpersonnel());
        personnelDTO.setAdressepersonnel(personnel.getAdressepersonnel());
        personnelDTO.setMotdepassepersonnel(personnel.getMotdepassepersonnel());
        return personnelDTO;
    }
}
