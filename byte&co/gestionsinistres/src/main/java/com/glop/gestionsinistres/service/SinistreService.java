package com.glop.gestionsinistres.service;

import com.glop.gestionsinistres.dto.SinistreDTO;

import java.util.List;
import java.util.Optional;

public interface SinistreService {

    List<SinistreDTO> getAllSinistres();

    List<SinistreDTO> getSinistresByUserId(String userId);

    Optional<SinistreDTO> getSinistreById(Long id);

    SinistreDTO createSinistre(SinistreDTO dto);

    Optional<SinistreDTO> updateSinistre(Long id, SinistreDTO dto);

    void deleteSinistre(Long id);

    List<SinistreDTO> getSinistresByStatut(String statut);
}
