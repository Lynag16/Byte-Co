package com.glop.gestionutilisateurs.services;

import com.glop.gestionutilisateurs.entities.Partenaire;
import com.glop.gestionutilisateurs.dtos.PartenaireDTO;
import com.glop.gestionutilisateurs.repositories.PartenaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartenaireService {

    @Autowired
    private PartenaireRepository partenaireRepository;

    // Convert Partenaire to PartenaireDTO
    private PartenaireDTO convertToDTO(Partenaire partenaire) {
        PartenaireDTO dto = new PartenaireDTO();
        dto.setIdPartenaire(partenaire.getIdPartenaire());
        dto.setNomPartenaire(partenaire.getNomPartenaire());
        dto.setzonegeo(partenaire.getzonegeo());
        dto.setEmailPartenaire(partenaire.getEmailPartenaire());
        dto.setTelephonePartenaire(partenaire.getTelephonePartenaire());
        dto.setTypeService(partenaire.getTypeService());
        dto.setAdressePartenaire(partenaire.getAdressePartenaire());
        dto.setMotdepassePartenaire(partenaire.getMotdepassePartenaire());
        return dto;
    }

    // Convert PartenaireDTO to Partenaire
    private Partenaire convertToEntity(PartenaireDTO partenaireDTO) {
        Partenaire partenaire = new Partenaire();
        partenaire.setNomPartenaire(partenaireDTO.getNomPartenaire());
        partenaire.setzonegeo(partenaireDTO.getzonegeo());
        partenaire.setEmailPartenaire(partenaireDTO.getEmailPartenaire());
        partenaire.setTelephonePartenaire(partenaireDTO.getTelephonePartenaire());
        partenaire.setMotdepassePartenaire(partenaireDTO.getMotdepassePartenaire());
        partenaire.setTypeService(partenaireDTO.getTypeService());
        partenaire.setAdressePartenaire(partenaireDTO.getAdressePartenaire());
        return partenaire;
    }

    // Fetch all partenaires and return DTOs
    public List<PartenaireDTO> getAllPartenaires() {
        List<Partenaire> partenaires = partenaireRepository.findAll();
        return partenaires.stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Fetch partenaire by ID and return DTO
    public PartenaireDTO getPartenaireById(int id) {
        Partenaire partenaire = partenaireRepository.findById(id).orElse(null);
        return (partenaire != null) ? convertToDTO(partenaire) : null;
    }

    // Create a new partenaire and return the DTO
    public PartenaireDTO createPartenaire(PartenaireDTO partenaireDTO) {
        Partenaire partenaire = convertToEntity(partenaireDTO);
        Partenaire savedPartenaire = partenaireRepository.save(partenaire);

        PartenaireDTO dto = convertToDTO(savedPartenaire);
        dto.setIdPartenaire(savedPartenaire.getIdPartenaire());
        return dto;
    }

    // Update a partenaire and return the updated DTO
    public PartenaireDTO updatePartenaire(int id, PartenaireDTO partenaireDTO) {
        Optional<Partenaire> existing = partenaireRepository.findById(id);
        if (existing.isPresent()) {
            Partenaire p = existing.get();
            p.setNomPartenaire(partenaireDTO.getNomPartenaire());
            p.setzonegeo(partenaireDTO.getzonegeo());
            p.setEmailPartenaire(partenaireDTO.getEmailPartenaire());
            p.setTelephonePartenaire(partenaireDTO.getTelephonePartenaire());
            p.setMotdepassePartenaire(partenaireDTO.getMotdepassePartenaire());
            p.setTypeService(partenaireDTO.getTypeService());
            p.setAdressePartenaire(partenaireDTO.getAdressePartenaire());
            Partenaire updatedPartenaire = partenaireRepository.save(p);
            return convertToDTO(updatedPartenaire);
        }
        return null;
    }

    // Delete partenaire by ID
    public void deletePartenaire(int id) {
        partenaireRepository.deleteById(id);
    }
}