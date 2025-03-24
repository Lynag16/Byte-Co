package com.glop.authentification.mappers;


import com.glop.authentification.dto.PersonnelDTO;
import com.glop.authentification.entities.Personnel;

public class PersonnelMapper {

    // Convertir l'entité Personnel en PersonnelDTO
    public static PersonnelDTO toDTO(Personnel personnel) {
        PersonnelDTO personnelDTO = new PersonnelDTO();
        personnelDTO.setIdpersonnel(personnel.getIdpersonnel());
        personnelDTO.setNompersonnel(personnel.getNompersonnel());
        personnelDTO.setPrenompersonnel(personnel.getPrenompersonnel());
        personnelDTO.setEmailpersonnel(personnel.getEmailpersonnel());
        personnelDTO.setTelephonepersonnel(personnel.getTelephonepersonnel());
        personnelDTO.setRolepersonnel(personnel.getRolepersonnel());
        personnelDTO.setDepartementpersonnel(personnel.getDepartementpersonnel());
        personnelDTO.setAdressepersonnel(personnel.getAdressepersonnel());
        personnelDTO.setMotdepassepersonnel(personnel.getmotdepassepersonnel());
        return personnelDTO;
    }

    // Convertir le PersonnelDTO en entité Personnel
    public static Personnel toEntity(PersonnelDTO personnelDTO) {
        Personnel personnel = new Personnel();
        personnel.setIdpersonnel(personnelDTO.getIdpersonnel());
        personnel.setNompersonnel(personnelDTO.getNompersonnel());
        personnel.setPrenompersonnel(personnelDTO.getPrenompersonnel());
        personnel.setEmailpersonnel(personnelDTO.getEmailpersonnel());
        personnel.setTelephonepersonnel(personnelDTO.getTelephonepersonnel());
        personnel.setRolepersonnel(personnelDTO.getRolepersonnel());
        personnel.setDepartementpersonnel(personnelDTO.getDepartementpersonnel());
        personnel.setAdressepersonnel(personnelDTO.getAdressepersonnel());
        personnel.setmotdepassepersonnel(personnelDTO.getMotdepassepersonnel());
        return personnel;
    }
}
