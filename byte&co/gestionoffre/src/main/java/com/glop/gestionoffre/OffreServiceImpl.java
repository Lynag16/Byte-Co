package com.glop.gestionoffre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OffreServiceImpl implements OffreService {

    @Autowired
    private OffreRepository offreRepository;

    @Override
    public List<OffreDTO> getAllOffres() {
        List<Offre> offres = offreRepository.findAll();
        return OffreMapper.toDTOList(offres);
    }

    @Override
    public OffreDTO getOffreById(Long id) {
        Offre offre = offreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offre not found"));
        return OffreMapper.toDTO(offre);
    }

    @Override
    public OffreDTO createOffre(OffreDTO offreDTO) {
        Offre offre = OffreMapper.fromDTO(offreDTO);
        Offre createdOffre = offreRepository.save(offre);
        return OffreMapper.toDTO(createdOffre);
    }

    @Override
    public OffreDTO updateOffre(Long id, OffreDTO offreDTO) {
        Offre existingOffre = offreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offre not found"));
        
        existingOffre.setNomoffre(offreDTO.getNomoffre());
        //existingOffre.setPrixoffre(offreDTO.getPrixoffre());
        existingOffre.setDescriptionoffre(offreDTO.getDescriptionoffre());
        existingOffre.setConditionseligibilite(offreDTO.getConditionseligibilite());
        existingOffre.setImageoffre(offreDTO.getImageoffre());
        existingOffre.setAvantagesoffre(offreDTO.getAvantagesoffre());

        Offre updatedOffre = offreRepository.save(existingOffre);
        return OffreMapper.toDTO(updatedOffre);
    }

    @Override
    public void deleteOffre(Long id) {
        if (!offreRepository.existsById(id)) {
            throw new RuntimeException("Offre not found");
        }
        offreRepository.deleteById(id);
    }
}
