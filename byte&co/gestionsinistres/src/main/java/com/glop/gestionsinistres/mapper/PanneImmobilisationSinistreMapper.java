package com.glop.gestionsinistres.mapper;

import com.glop.gestionsinistres.dto.PanneImmobilisationSinistreDTO;
import com.glop.gestionsinistres.model.PanneImmobilisationSinistre;

public class PanneImmobilisationSinistreMapper {

    public static PanneImmobilisationSinistre toEntity(PanneImmobilisationSinistreDTO dto) {
        PanneImmobilisationSinistre sinistre = new PanneImmobilisationSinistre();
        SinistreMapper.mapCommonFields(dto, sinistre);
        sinistre.setNaturePanne(dto.getNaturePanne());
        sinistre.setBesoinDepannage(dto.isBesoinDepannage());
        return sinistre;
    }
}
