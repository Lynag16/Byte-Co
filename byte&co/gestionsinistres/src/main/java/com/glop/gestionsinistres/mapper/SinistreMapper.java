package com.glop.gestionsinistres.mapper;

import com.glop.gestionsinistres.dto.SinistreDTO;
import com.glop.gestionsinistres.model.Sinistre;
import com.glop.gestionsinistres.model.StatutSinistre;
import com.glop.gestionsinistres.model.TypeSinistre;

public class SinistreMapper {

    public static SinistreDTO toDTO(Sinistre sinistre) {
        SinistreDTO dto = new SinistreDTO();
        dto.setId(sinistre.getId());
        dto.setDescription(sinistre.getDescription());
        dto.setType(sinistre.getType().name());
        dto.setDateDeclaration(sinistre.getDateDeclaration());
        dto.setMontantEstime(sinistre.getMontantEstime());
        dto.setStatut(sinistre.getStatut().name());
        dto.setUserId(sinistre.getUserId());
        return dto;
    }

    public static Sinistre toEntity(SinistreDTO dto) {
        Sinistre sinistre = new Sinistre();
        sinistre.setId(dto.getId());
        sinistre.setDescription(dto.getDescription());
        sinistre.setType(TypeSinistre.valueOf(dto.getType()));
        sinistre.setDateDeclaration(dto.getDateDeclaration());
        sinistre.setMontantEstime(dto.getMontantEstime());
        sinistre.setStatut(StatutSinistre.valueOf(dto.getStatut()));
        sinistre.setUserId(dto.getUserId());
        return sinistre;
    }
}
