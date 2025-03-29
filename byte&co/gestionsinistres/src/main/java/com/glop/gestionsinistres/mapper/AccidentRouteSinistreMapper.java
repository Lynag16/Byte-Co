package com.glop.gestionsinistres.mapper;

import com.glop.gestionsinistres.dto.AccidentRouteSinistreDTO;
import com.glop.gestionsinistres.model.AccidentRouteSinistre;

public class AccidentRouteSinistreMapper {

    public static AccidentRouteSinistre toEntity(AccidentRouteSinistreDTO dto, String filePath) {
        AccidentRouteSinistre sinistre = new AccidentRouteSinistre();
        SinistreMapper.mapCommonFields(dto, sinistre);
        sinistre.setLieuAccident(dto.getLieuAccident());
        sinistre.setConstatFilePath(filePath);
        sinistre.setImmatriculation(dto.getImmatriculation());
        return sinistre;
    }

    public static AccidentRouteSinistre toEntity(AccidentRouteSinistreDTO dto) {
        AccidentRouteSinistre sinistre = new AccidentRouteSinistre();
        SinistreMapper.mapCommonFields(dto, sinistre);
        sinistre.setLieuAccident(dto.getLieuAccident());
        sinistre.setImmatriculation(dto.getImmatriculation());
        return sinistre;
    }
}
