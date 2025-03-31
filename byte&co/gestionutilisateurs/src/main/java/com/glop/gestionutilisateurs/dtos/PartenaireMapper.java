package com.glop.gestionutilisateurs.dtos;

import com.glop.gestionutilisateurs.entities.Partenaire;

public class PartenaireMapper {

    public static PartenaireDTO toDTO(Partenaire partenaire) {
        if (partenaire == null) return null;

        PartenaireDTO dto = new PartenaireDTO();
        dto.setIdPartenaire(partenaire.getIdPartenaire());
        dto.setNomPartenaire(partenaire.getNomPartenaire());
        dto.setZonegeo(partenaire.getzonegeo());
        dto.setEmailPartenaire(partenaire.getEmailPartenaire());
        dto.setTelephonePartenaire(partenaire.getTelephonePartenaire());
        dto.setTypeService(partenaire.getTypeService());
        dto.setAdressePartenaire(partenaire.getAdressePartenaire());

        return dto;
    }

    public static Partenaire toEntity(PartenaireDTO dto) {
        if (dto == null) return null;

        Partenaire partenaire = new Partenaire();
        partenaire.setIdPartenaire(dto.getIdPartenaire());
        partenaire.setNomPartenaire(dto.getNomPartenaire());
        partenaire.setzonegeo(dto.getZonegeo());
        partenaire.setEmailPartenaire(dto.getEmailPartenaire());
        partenaire.setTelephonePartenaire(dto.getTelephonePartenaire());
        partenaire.setTypeService(dto.getTypeService());
        partenaire.setAdressePartenaire(dto.getAdressePartenaire());

        return partenaire;
    }
}