package com.glop.gestionoffre;

import java.util.List;
import java.util.stream.Collectors;

public class OffreMapper {

    public static OffreDTO toDTO(Offre offre) {
        OffreDTO dto = new OffreDTO();
        dto.setId(offre.getId());
        dto.setNomoffre(offre.getNomoffre());
        //dto.setPrixoffre(offre.getPrixoffre());
        dto.setDescriptionoffre(offre.getDescriptionoffre());
        dto.setConditionseligibilite(offre.getConditionseligibilite());
        dto.setImageoffre(offre.getImageoffre());
        dto.setAvantagesoffre(offre.getAvantagesoffre());
        return dto;
    }

    public static List<OffreDTO> toDTOList(List<Offre> offres) {
        return offres.stream().map(OffreMapper::toDTO).collect(Collectors.toList());
    }

    public static Offre fromDTO(OffreDTO offreDTO) {
        Offre offre = new Offre();
        offre.setId(offreDTO.getId());
        offre.setNomoffre(offreDTO.getNomoffre());
        //offre.setPrixoffre(offreDTO.getPrixoffre());
        offre.setDescriptionoffre(offreDTO.getDescriptionoffre());
        offre.setConditionseligibilite(offreDTO.getConditionseligibilite());
        offre.setImageoffre(offreDTO.getImageoffre());
        offre.setAvantagesoffre(offreDTO.getAvantagesoffre());
        return offre;
    }
}
