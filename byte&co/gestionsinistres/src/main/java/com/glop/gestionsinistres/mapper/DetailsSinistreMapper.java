package com.glop.gestionsinistres.mapper;

import com.glop.gestionsinistres.dto.DetailsSinistreDTO;
import com.glop.gestionsinistres.model.DetailsSinistre;
import com.glop.gestionsinistres.model.Sinistre;

public class DetailsSinistreMapper {

    public static DetailsSinistreDTO toDTO(DetailsSinistre entity) {
        DetailsSinistreDTO dto = new DetailsSinistreDTO();
        dto.setId(entity.getId());
        dto.setSinistreId(entity.getSinistre() != null ? entity.getSinistre().getId() : null);

        dto.setObjetPerdu(entity.getObjetPerdu());
        dto.setLieuIncident(entity.getLieuIncident());
        dto.setValeurEstimee(entity.getValeurEstimee());

        dto.setNombreBlesses(entity.getNombreBlesses());
        dto.setNomHopital(entity.getNomHopital());

        dto.setPanneType(entity.getPanneType());
        dto.setLieuHebergement(entity.getLieuHebergement());

        dto.setRetardHeures(entity.getRetardHeures());
        dto.setTransportConcerne(entity.getTransportConcerne());

        dto.setObjetEndommage(entity.getObjetEndommage());
        dto.setDegatsEstimes(entity.getDegatsEstimes());

        return dto;
    }

    public static DetailsSinistre toEntity(DetailsSinistreDTO dto, Sinistre sinistre) {
        DetailsSinistre entity = new DetailsSinistre();
        entity.setId(dto.getId());
        entity.setSinistre(sinistre); // association à l’entité Sinistre

        entity.setObjetPerdu(dto.getObjetPerdu());
        entity.setLieuIncident(dto.getLieuIncident());
        entity.setValeurEstimee(dto.getValeurEstimee());

        entity.setNombreBlesses(dto.getNombreBlesses());
        entity.setNomHopital(dto.getNomHopital());

        entity.setPanneType(dto.getPanneType());
        entity.setLieuHebergement(dto.getLieuHebergement());

        entity.setRetardHeures(dto.getRetardHeures());
        entity.setTransportConcerne(dto.getTransportConcerne());

        entity.setObjetEndommage(dto.getObjetEndommage());
        entity.setDegatsEstimes(dto.getDegatsEstimes());

        return entity;
    }
}
