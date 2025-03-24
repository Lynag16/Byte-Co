package com.glop.gestionoffre;


import com.glop.gestionoffre.Offre;
import com.glop.gestionoffre.OffreRepository;
import com.glop.gestionoffre.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OffreServiceImpl implements OffreService {

    @Autowired
    private OffreRepository offreRepository;

    @Override
    public List<Offre> getAllOffres() {
        return offreRepository.findAll();
    }

    @Override
    public Offre getOffreById(Long id) {
        return offreRepository.findById(id).orElseThrow(() -> new RuntimeException("Offre not found"));
    }

    @Override
    public Offre createOffre(Offre offre) {
        return offreRepository.save(offre);
    }

    @Override
    public Offre updateOffre(Long id, Offre offre) {
        Offre existingOffre = getOffreById(id);
        existingOffre.setNomoffre(offre.getNomoffre());
        existingOffre.setPrixoffre(offre.getPrixoffre());
        existingOffre.setDescriptionoffre(offre.getDescriptionoffre());
        existingOffre.setConditionseligibilite(offre.getConditionseligibilite());
        return offreRepository.save(existingOffre);
    }

    @Override
    public void deleteOffre(Long id) {
        offreRepository.deleteById(id);
    }
}
