package com.glop.gestionsinistres.mapper;

import com.glop.gestionsinistres.dto.RetardTransportSinistreDTO;
import com.glop.gestionsinistres.model.RetardTransportSinistre;

public class RetardTransportSinistreMapper {

    public static RetardTransportSinistre toEntity(RetardTransportSinistreDTO dto) {
        RetardTransportSinistre sinistre = new RetardTransportSinistre();
        SinistreMapper.mapCommonFields(dto, sinistre);
        sinistre.setMoyenTransport(dto.getMoyenTransport());
        sinistre.setDureeRetardMinutes(dto.getDureeRetardMinutes());
        return sinistre;
    }
}
