package com.glop.gestionsinistres.mapper;

import com.glop.gestionsinistres.dto.sinistre.ProblemeHebergementSinistreDTO;
import com.glop.gestionsinistres.model.sinistre.ProblemeHebergementSinistre;

public class ProblemeHebergementSinistreMapper {

    public static ProblemeHebergementSinistre toEntity(ProblemeHebergementSinistreDTO dto) {
        ProblemeHebergementSinistre sinistre = new ProblemeHebergementSinistre();
        SinistreMapper.mapCommonFields(dto, sinistre);
        sinistre.setNomHotel(dto.getNomHotel());
        sinistre.setNatureProbleme(dto.getNatureProbleme());
        return sinistre;
    }
}
