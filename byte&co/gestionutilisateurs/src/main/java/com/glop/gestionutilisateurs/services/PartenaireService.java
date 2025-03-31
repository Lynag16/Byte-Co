package com.glop.gestionutilisateurs.services;

import com.glop.gestionutilisateurs.dtos.PartenaireMapper;
import com.glop.gestionutilisateurs.dtos.PersonnelDTO;
import com.glop.gestionutilisateurs.entities.Partenaire;
import com.glop.gestionutilisateurs.dtos.PartenaireDTO;
import com.glop.gestionutilisateurs.entities.Personnel;
import com.glop.gestionutilisateurs.repositories.PartenaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartenaireService {

    @Autowired
    private PartenaireRepository partenaireRepository;

    public List<PartenaireDTO> getAllPartenaires() {
        return partenaireRepository.findAll().stream()
                .map(PartenaireMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<PartenaireDTO> getPartenaireById(int id) {
        return partenaireRepository.findById(id)
                .map(PartenaireMapper::toDTO);
    }

    public PartenaireDTO createPartenaire(PartenaireDTO dto) {
        Partenaire partenaire = PartenaireMapper.toEntity(dto);
        Partenaire saved = partenaireRepository.save(partenaire);
        return PartenaireMapper.toDTO(saved);
    }

    public Optional<PartenaireDTO> updatePartenaire(int id, PartenaireDTO dto) {
        return partenaireRepository.findById(id).map(existing -> {
            existing.setNomPartenaire(dto.getNomPartenaire());
            existing.setzonegeo(dto.getZonegeo());
            existing.setEmailPartenaire(dto.getEmailPartenaire());
            existing.setTelephonePartenaire(dto.getTelephonePartenaire());
            existing.setTypeService(dto.getTypeService());
            existing.setAdressePartenaire(dto.getAdressePartenaire());
            return PartenaireMapper.toDTO(partenaireRepository.save(existing));
        });
    }

    public boolean deletePartenaire(int id) {
        if (partenaireRepository.existsById(id)) {
            partenaireRepository.deleteById(id);
            return true;
        }
        return false;
    }

}