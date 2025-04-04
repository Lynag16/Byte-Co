package com.glop.gestionsinistres.mapper.sinistre;

import com.glop.gestionsinistres.dto.sinistre.AccidentRouteSinistreDTO;
import com.glop.gestionsinistres.model.sinistre.AccidentRouteSinistre;

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
