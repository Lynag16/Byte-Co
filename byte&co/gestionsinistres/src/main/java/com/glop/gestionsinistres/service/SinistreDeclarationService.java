package com.glop.gestionsinistres.service;

import com.glop.gestionsinistres.dto.AccidentRouteSinistreDTO;
import com.glop.gestionsinistres.dto.PanneImmobilisationSinistreDTO;
import com.glop.gestionsinistres.dto.VolOuPerteObjetSinistreDTO;
import com.glop.gestionsinistres.dto.RetardTransportSinistreDTO;
import com.glop.gestionsinistres.mapper.AccidentRouteSinistreMapper;
import com.glop.gestionsinistres.mapper.PanneImmobilisationSinistreMapper;
import com.glop.gestionsinistres.mapper.VolOuPerteObjetSinistreMapper;
import com.glop.gestionsinistres.mapper.RetardTransportSinistreMapper;
import com.glop.gestionsinistres.model.*;
import com.glop.gestionsinistres.repository.SinistreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class SinistreDeclarationService {

    private final SinistreRepository sinistreRepository;
    private final FileStorageService fileStorageService;
    private static final Logger log = LoggerFactory.getLogger(SinistreDeclarationService.class);

    public SinistreDeclarationService(SinistreRepository sinistreRepository, FileStorageService fileStorageService) {
        this.sinistreRepository = sinistreRepository;
        this.fileStorageService = fileStorageService;
    }

    private String logAndGetUserId(String description, LocalDateTime dateDeclaration) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Déclaration sinistre par utilisateur: {}", userId);
        log.info("Description: {}", description);
        log.info("Date déclaration: {}", dateDeclaration);
        return userId;
    }

    private <T extends Sinistre> T saveSinistre(T sinistre) {
        T saved = sinistreRepository.save(sinistre);
        log.info("Sinistre enregistré ID: {}", saved.getId());
        return saved;
    }

    private String handleFileUpload(MultipartFile file, String label) throws IOException {
        if (file != null && !file.isEmpty()) {
            log.info("Fichier {}: {}", label, file.getOriginalFilename());
            String filePath = fileStorageService.storeFile(file);
            log.info("Fichier stocké: {}", filePath);
            return filePath;
        } else {
            log.warn("Aucun fichier {} fourni.", label);
            return null;
        }
    }

    public AccidentRouteSinistre declarerAccident(AccidentRouteSinistreDTO dto) throws IOException {
        String userId = logAndGetUserId(dto.getDescription(), dto.getDateDeclaration());
        String filePath = handleFileUpload(dto.getConstat(), "constat");
        AccidentRouteSinistre sinistre = (filePath != null)
                ? AccidentRouteSinistreMapper.toEntity(dto, filePath)
                : AccidentRouteSinistreMapper.toEntity(dto);
        sinistre.setUserId(userId);
        return saveSinistre(sinistre);
    }

    public PanneImmobilisationSinistre declarerPanne(PanneImmobilisationSinistreDTO dto) {
        String userId = logAndGetUserId(dto.getDescription(), dto.getDateDeclaration());
        PanneImmobilisationSinistre sinistre = PanneImmobilisationSinistreMapper.toEntity(dto);
        sinistre.setUserId(userId);
        return saveSinistre(sinistre);
    }

    public VolOuPerteObjetSinistre declarerVol(VolOuPerteObjetSinistreDTO dto) throws IOException {
        String userId = logAndGetUserId(dto.getDescription(), dto.getDateDeclaration());
        String filePath = handleFileUpload(dto.getDeclarationPolice(), "declarationPolice");

        VolOuPerteObjetSinistre sinistre = (filePath != null)
                ? VolOuPerteObjetSinistreMapper.toEntity(dto, filePath)
                : VolOuPerteObjetSinistreMapper.toEntity(dto);
        sinistre.setUserId(userId);
        return saveSinistre(sinistre);
    }

    public RetardTransportSinistre declarerRetardTransport(RetardTransportSinistreDTO dto) {
        String userId = logAndGetUserId(dto.getDescription(), dto.getDateDeclaration());
        RetardTransportSinistre sinistre = RetardTransportSinistreMapper.toEntity(dto);
        sinistre.setUserId(userId);
        return saveSinistre(sinistre);
    }

}
