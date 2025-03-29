package com.glop.gestionsinistres.mapper;

import com.glop.gestionsinistres.dto.VolOuPerteObjetSinistreDTO;
import com.glop.gestionsinistres.model.VolOuPerteObjetSinistre;

public class VolOuPerteObjetSinistreMapper {

    public static VolOuPerteObjetSinistre toEntity(VolOuPerteObjetSinistreDTO dto, String filePath) {
        VolOuPerteObjetSinistre sinistre = new VolOuPerteObjetSinistre();
        SinistreMapper.mapCommonFields(dto, sinistre);
        sinistre.setLieuVol(dto.getLieuVol());
        sinistre.setDescriptionObjetPerdu(dto.getDescriptionObjetPerdu());
        sinistre.setValeurObjetPerdu(dto.getValeurObjetPerdu());
        sinistre.setDeclarationPoliceFilePath(filePath);
        return sinistre;
    }

    public static VolOuPerteObjetSinistre toEntity(VolOuPerteObjetSinistreDTO dto) {
        VolOuPerteObjetSinistre sinistre = new VolOuPerteObjetSinistre();
        SinistreMapper.mapCommonFields(dto, sinistre);
        sinistre.setLieuVol(dto.getLieuVol());
        sinistre.setDescriptionObjetPerdu(dto.getDescriptionObjetPerdu());
        sinistre.setValeurObjetPerdu(dto.getValeurObjetPerdu());
        return sinistre;
    }
}
