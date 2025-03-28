package com.glop.gestionsinistres.service;

import com.glop.gestionsinistres.dto.AccidentRouteSinistreDTO;
import com.glop.gestionsinistres.mapper.AccidentRouteSinistreMapper;
import com.glop.gestionsinistres.model.AccidentRouteSinistre;
import com.glop.gestionsinistres.repository.SinistreRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SinistreDeclarationService {

    private final SinistreRepository sinistreRepository;
    private final FileStorageService fileStorageService;
    private static final Logger log = LoggerFactory.getLogger(SinistreDeclarationService.class);

    public SinistreDeclarationService(SinistreRepository sinistreRepository, FileStorageService fileStorageService) {
        this.sinistreRepository = sinistreRepository;
        this.fileStorageService = fileStorageService;
    }

    public AccidentRouteSinistre declarerAccident(AccidentRouteSinistreDTO dto) throws IOException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Déclaration sinistre par utilisateur: {}", userId);
        log.info("Description: {}", dto.getDescription());
        log.info("Date déclaration: {}", dto.getDateDeclaration());

        AccidentRouteSinistre sinistre;

        if (dto.getConstat() != null && !dto.getConstat().isEmpty()) {
            log.info("Fichier constat: {}", dto.getConstat().getOriginalFilename());
            String filePath = fileStorageService.storeFile(dto.getConstat());
            sinistre = AccidentRouteSinistreMapper.toEntity(dto, filePath);
            log.info("Fichier stocké: {}", filePath);
        } else {
            log.warn("Aucun fichier constat fourni.");
            sinistre = AccidentRouteSinistreMapper.toEntity(dto);
        }

        sinistre.setUserId(userId);
        AccidentRouteSinistre saved = sinistreRepository.save(sinistre);
        log.info("Sinistre enregistré ID: {}", saved.getId());

        return saved;
    }

}
