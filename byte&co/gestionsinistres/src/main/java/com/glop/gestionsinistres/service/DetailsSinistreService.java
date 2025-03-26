package com.glop.gestionsinistres.service;

import com.glop.gestionsinistres.dto.DetailsSinistreDTO;

import java.util.Optional;

public interface DetailsSinistreService {

    Optional<DetailsSinistreDTO> getDetailsBySinistreId(Long sinistreId);

    DetailsSinistreDTO createOrUpdateDetails(Long sinistreId, DetailsSinistreDTO dto);

    void deleteDetailsBySinistreId(Long sinistreId);
}
