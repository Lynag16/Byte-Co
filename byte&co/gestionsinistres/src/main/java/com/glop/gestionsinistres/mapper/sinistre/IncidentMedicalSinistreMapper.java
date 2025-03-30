package com.glop.gestionsinistres.mapper;

import com.glop.gestionsinistres.dto.sinistre.IncidentMedicalSinistreDTO;
import com.glop.gestionsinistres.model.sinistre.IncidentMedicalSinistre;

public class IncidentMedicalSinistreMapper {

    public static IncidentMedicalSinistre toEntity(IncidentMedicalSinistreDTO dto, String filePath) {
        IncidentMedicalSinistre sinistre = new IncidentMedicalSinistre();
        SinistreMapper.mapCommonFields(dto, sinistre);
        sinistre.setSymptomes(dto.getSymptomes());
        sinistre.setDossierMedicalFilePath(filePath);
        sinistre.setCoutIntervention(dto.getCoutIntervention());
        sinistre.setTypeIntervention(dto.getTypeIntervention());
        return sinistre;
    }

    public static IncidentMedicalSinistre toEntity(IncidentMedicalSinistreDTO dto) {
        IncidentMedicalSinistre sinistre = new IncidentMedicalSinistre();
        SinistreMapper.mapCommonFields(dto, sinistre);
        sinistre.setCoutIntervention(dto.getCoutIntervention());
        sinistre.setTypeIntervention(dto.getTypeIntervention());
        return sinistre;
    }
}
