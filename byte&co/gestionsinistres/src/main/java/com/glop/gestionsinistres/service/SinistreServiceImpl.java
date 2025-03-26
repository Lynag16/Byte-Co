package com.glop.gestionsinistres.service;

import com.glop.gestionsinistres.dto.SinistreDTO;
import com.glop.gestionsinistres.mapper.SinistreMapper;
import com.glop.gestionsinistres.model.Sinistre;
import com.glop.gestionsinistres.repository.SinistreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SinistreServiceImpl implements SinistreService {

    private final SinistreRepository sinistreRepository;

    public SinistreServiceImpl(SinistreRepository sinistreRepository) {
        this.sinistreRepository = sinistreRepository;
    }

    @Override
    public List<SinistreDTO> getAllSinistres() {
        return sinistreRepository.findAll()
                .stream()
                .map(SinistreMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SinistreDTO> getSinistresByUserId(String userId) {
        return sinistreRepository.findByUserId(userId)
                .stream()
                .map(SinistreMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SinistreDTO> getSinistreById(Long id) {
        return sinistreRepository.findById(id)
                .map(SinistreMapper::toDTO);
    }

    @Override
    public SinistreDTO createSinistre(SinistreDTO dto) {
        Sinistre sinistre = SinistreMapper.toEntity(dto);
        Sinistre saved = sinistreRepository.save(sinistre);
        return SinistreMapper.toDTO(saved);
    }

    @Override
    public Optional<SinistreDTO> updateSinistre(Long id, SinistreDTO dto) {
        return sinistreRepository.findById(id).map(existing -> {
            existing.setDescription(dto.getDescription());
            existing.setType(SinistreMapper.toEntity(dto).getType());
            existing.setDateDeclaration(dto.getDateDeclaration());
            existing.setMontantEstime(dto.getMontantEstime());
            existing.setStatut(SinistreMapper.toEntity(dto).getStatut());
            Sinistre updated = sinistreRepository.save(existing);
            return SinistreMapper.toDTO(updated);
        });
    }

    @Override
    public void deleteSinistre(Long id) {
        sinistreRepository.deleteById(id);
    }

    @Override
    public List<SinistreDTO> getSinistresByStatut(String statut) {
        return sinistreRepository.findAll().stream()
                .filter(s -> s.getStatut().name().equalsIgnoreCase(statut))
                .map(SinistreMapper::toDTO)
                .collect(Collectors.toList());
    }

}
