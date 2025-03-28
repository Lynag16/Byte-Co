package com.glop.gestionsinistres.mapper;

import com.glop.gestionsinistres.dto.SinistreDTO;
import com.glop.gestionsinistres.model.Sinistre;

public class SinistreMapper {

    public static void mapCommonFields(SinistreDTO dto, Sinistre entity) {
        entity.setDescription(dto.getDescription());
        entity.setDateDeclaration(dto.getDateDeclaration());
    }
}
