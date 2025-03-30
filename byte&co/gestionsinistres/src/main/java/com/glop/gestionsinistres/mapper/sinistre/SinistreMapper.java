package com.glop.gestionsinistres.mapper.sinistre;

import com.glop.gestionsinistres.dto.sinistre.SinistreDTO;
import com.glop.gestionsinistres.model.sinistre.Sinistre;

public class SinistreMapper {

    public static void mapCommonFields(SinistreDTO dto, Sinistre entity) {
        entity.setDescription(dto.getDescription());
        entity.setDateDeclaration(dto.getDateDeclaration());
    }
}
