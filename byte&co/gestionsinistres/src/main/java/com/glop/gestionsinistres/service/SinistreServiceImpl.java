package com.glop.gestionsinistres.service;

import java.util.List;
import java.util.Optional;

import com.glop.gestionsinistres.model.Sinistre;
import com.glop.gestionsinistres.repository.SinistreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SinistreServiceImpl {

    @Autowired
    private SinistreRepository sinistreRepository;

    public List<Sinistre> getAllSinistres() {
        return sinistreRepository.findAll();
    }

    public Optional<Sinistre> getSinistreById(Long id) {
        return sinistreRepository.findById(id);
    }

    public Sinistre createSinistre(Sinistre sinistre) {
        return sinistreRepository.save(sinistre);
    }

    public Sinistre updateSinistre(Long id, Sinistre sinistreDetails) {
        Optional<Sinistre> sinistre = sinistreRepository.findById(id);
        if (sinistre.isPresent()) {
            Sinistre updatedSinistre = sinistre.get();
            updatedSinistre.setDescription(sinistreDetails.getDescription());
            updatedSinistre.setType(sinistreDetails.getType());
            updatedSinistre.setDateDeclaration(sinistreDetails.getDateDeclaration());
            updatedSinistre.setMontantEstime(sinistreDetails.getMontantEstime());
            updatedSinistre.setStatut(sinistreDetails.getStatut());
            return sinistreRepository.save(updatedSinistre);
        }
        return null;
    }

    public void deleteSinistre(Long id) {
        sinistreRepository.deleteById(id);
    }
}
