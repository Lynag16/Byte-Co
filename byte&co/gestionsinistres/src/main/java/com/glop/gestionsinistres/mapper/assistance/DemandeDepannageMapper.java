package com.glop.gestionsinistres.mapper.assistance;

import com.glop.gestionsinistres.dto.assistance.DemandeDepannageDTO;
import com.glop.gestionsinistres.model.assistance.DemandeDepannage;

public class DemandeDepannageMapper {

    public DemandeDepannageDTO toDTO(DemandeDepannage demandeDepannage) {
        DemandeDepannageDTO dto = new DemandeDepannageDTO();
        dto.setUserId(demandeDepannage.getUserId());
        dto.setImmatriculation(demandeDepannage.getImmatriculation());
        dto.setCategorieProbleme(demandeDepannage.getCategorieProbleme());
        dto.setTypeEnergie(demandeDepannage.getTypeEnergie());
        dto.setAdresse(demandeDepannage.getAdresse());
        dto.setSurAutoroute(demandeDepannage.isSurAutoroute());
        dto.setDateIncident(demandeDepannage.getDateIncident());
        dto.setDateSouhaiteeIntervention(demandeDepannage.getDateSouhaiteeIntervention());
        dto.setPrenomContact(demandeDepannage.getPrenomContact());
        dto.setNomContact(demandeDepannage.getNomContact());
        dto.setTelephoneContact(demandeDepannage.getTelephoneContact());
        dto.setStatut(demandeDepannage.getStatut());
        dto.setDateCreation(demandeDepannage.getDateCreation());
        return dto;
    }

    public static DemandeDepannage toEntity(DemandeDepannageDTO demandeDepannageDTO) {
        DemandeDepannage entity = new DemandeDepannage();
        entity.setUserId(demandeDepannageDTO.getUserId());
        entity.setImmatriculation(demandeDepannageDTO.getImmatriculation());
        entity.setCategorieProbleme(demandeDepannageDTO.getCategorieProbleme());
        entity.setTypeEnergie(demandeDepannageDTO.getTypeEnergie());
        entity.setAdresse(demandeDepannageDTO.getAdresse());
        entity.setSurAutoroute(demandeDepannageDTO.isSurAutoroute());
        entity.setDateIncident(demandeDepannageDTO.getDateIncident());
        entity.setDateSouhaiteeIntervention(demandeDepannageDTO.getDateSouhaiteeIntervention());
        entity.setPrenomContact(demandeDepannageDTO.getPrenomContact());
        entity.setNomContact(demandeDepannageDTO.getNomContact());
        entity.setTelephoneContact(demandeDepannageDTO.getTelephoneContact());
        entity.setStatut(demandeDepannageDTO.getStatut());
        entity.setDateCreation(demandeDepannageDTO.getDateCreation());
        return entity;
    }
}
