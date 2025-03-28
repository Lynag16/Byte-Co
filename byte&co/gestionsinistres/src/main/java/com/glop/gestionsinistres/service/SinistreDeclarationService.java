package com.glop.gestionsinistres.service;

import com.glop.gestionsinistres.dto.AccidentRouteSinistreDTO;
import com.glop.gestionsinistres.dto.PanneImmobilisationSinistreDTO;
import com.glop.gestionsinistres.dto.VolOuPerteObjetSinistreDTO;
import com.glop.gestionsinistres.mapper.AccidentRouteSinistreMapper;
import com.glop.gestionsinistres.mapper.PanneImmobilisationSinistreMapper;
import com.glop.gestionsinistres.mapper.VolOuPerteObjetSinistreMapper;
import com.glop.gestionsinistres.model.AccidentRouteSinistre;
import com.glop.gestionsinistres.model.PanneImmobilisationSinistre;
import com.glop.gestionsinistres.model.VolOuPerteObjetSinistre;
import com.glop.gestionsinistres.repository.SinistreRepository;
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

    public PanneImmobilisationSinistre declarerPanne(PanneImmobilisationSinistreDTO dto) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Déclaration sinistre par utilisateur: {}", userId);
        log.info("Description: {}", dto.getDescription());
        log.info("Date déclaration: {}", dto.getDateDeclaration());

        PanneImmobilisationSinistre sinistre = PanneImmobilisationSinistreMapper.toEntity(dto);
        sinistre.setUserId(userId);
        PanneImmobilisationSinistre saved = sinistreRepository.save(sinistre);
        log.info("Sinistre enregistré ID: {}", saved.getId());

        return saved;
    }

    public VolOuPerteObjetSinistre declarerVol(VolOuPerteObjetSinistreDTO dto) throws IOException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Déclaration sinistre par utilisateur: {}", userId);
        log.info("Description: {}", dto.getDescription());
        log.info("Date déclaration: {}", dto.getDateDeclaration());

        VolOuPerteObjetSinistre sinistre;

        if (dto.getDeclarationPolice() != null && !dto.getDeclarationPolice().isEmpty()) {
            log.info("Fichier déclaration police: {}", dto.getDeclarationPolice().getOriginalFilename());
            String filePath = fileStorageService.storeFile(dto.getDeclarationPolice());
            sinistre = VolOuPerteObjetSinistreMapper.toEntity(dto, filePath);
            log.info("Fichier stocké: {}", filePath);
            sinistre.setUserId(userId);
        }
        else {
            log.warn("Aucun fichier déclaration police fourni.");
            sinistre = VolOuPerteObjetSinistreMapper.toEntity(dto);
        }
        sinistre.setUserId(userId);
        VolOuPerteObjetSinistre saved = sinistreRepository.save(sinistre);
        log.info("Sinistre enregistré ID: {}", saved.getId());

        return saved;
    }

}
