package com.glop.authentification.mappers;

import com.glop.authentification.entities.Partenaire;
import com.glop.authentification.dto.PartenaireDTO;

public class PartenaireMapper {

    // Convertir Partenaire en PartenaireDTO
    public static PartenaireDTO toDTO(Partenaire partenaire) {
        if (partenaire == null) {
            return null;
        }
        PartenaireDTO partenaireDTO = new PartenaireDTO();
        partenaireDTO.setIdPartenaire(partenaire.getIdPartenaire());
        partenaireDTO.setNomPartenaire(partenaire.getNomPartenaire());
        partenaireDTO.setPrenomPartenaire(partenaire.getPrenomPartenaire());
        partenaireDTO.setEmailPartenaire(partenaire.getEmailPartenaire());
        partenaireDTO.setTelephonePartenaire(partenaire.getTelephonePartenaire());
        partenaireDTO.setTypeService(partenaire.getTypeService());
        partenaireDTO.setAdressePartenaire(partenaire.getAdressePartenaire());
        return partenaireDTO;
    }

    // Convertir PartenaireDTO en Partenaire
    public static Partenaire toEntity(PartenaireDTO partenaireDTO) {
        if (partenaireDTO == null) {
            return null;
        }
        Partenaire partenaire = new Partenaire();
        partenaire.setIdPartenaire(partenaireDTO.getIdPartenaire());
        partenaire.setNomPartenaire(partenaireDTO.getNomPartenaire());
        partenaire.setPrenomPartenaire(partenaireDTO.getPrenomPartenaire());
        partenaire.setEmailPartenaire(partenaireDTO.getEmailPartenaire());
        partenaire.setTelephonePartenaire(partenaireDTO.getTelephonePartenaire());
        partenaire.setTypeService(partenaireDTO.getTypeService());
        partenaire.setAdressePartenaire(partenaireDTO.getAdressePartenaire());
        return partenaire;
    }
}
