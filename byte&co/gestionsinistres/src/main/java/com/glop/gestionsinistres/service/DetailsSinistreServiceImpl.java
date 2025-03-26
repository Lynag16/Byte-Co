package com.glop.gestionsinistres.service;

import com.glop.gestionsinistres.dto.DetailsSinistreDTO;
import com.glop.gestionsinistres.mapper.DetailsSinistreMapper;
import com.glop.gestionsinistres.model.DetailsSinistre;
import com.glop.gestionsinistres.model.Sinistre;
import com.glop.gestionsinistres.repository.DetailsSinistreRepository;
import com.glop.gestionsinistres.repository.SinistreRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetailsSinistreServiceImpl implements  DetailsSinistreService {

    private final DetailsSinistreRepository detailsRepo;
    private final SinistreRepository sinistreRepo;

    public DetailsSinistreServiceImpl(DetailsSinistreRepository detailsRepo, SinistreRepository sinistreRepo) {
        this.detailsRepo = detailsRepo;
        this.sinistreRepo = sinistreRepo;
    }

    public Optional<DetailsSinistreDTO> getDetailsBySinistreId(Long sinistreId) {
        return Optional.ofNullable(detailsRepo.findBySinistreId(sinistreId))
                .map(DetailsSinistreMapper::toDTO);
    }

    public DetailsSinistreDTO createOrUpdateDetails(Long sinistreId, DetailsSinistreDTO dto) {
        Sinistre sinistre = sinistreRepo.findById(sinistreId)
                .orElseThrow(() -> new IllegalArgumentException("Sinistre non trouvé"));

        DetailsSinistre details = DetailsSinistreMapper.toEntity(dto, sinistre);
        details.setId(sinistreId); // garantir cohérence @MapsId
        DetailsSinistre saved = detailsRepo.save(details);

        return DetailsSinistreMapper.toDTO(saved);
    }

    public void deleteDetailsBySinistreId(Long sinistreId) {
        DetailsSinistre details = detailsRepo.findBySinistreId(sinistreId);
        if (details != null) {
            detailsRepo.delete(details);
        }
    }
}
