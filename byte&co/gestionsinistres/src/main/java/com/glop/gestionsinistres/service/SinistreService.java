package com.glop.gestionsinistres.service;

import com.glop.gestionsinistres.dto.sinistre.*;
import com.glop.gestionsinistres.mapper.sinistre.*;
import com.glop.gestionsinistres.model.assistance.AffectationAssistance;
import com.glop.gestionsinistres.model.sinistre.*;
import com.glop.gestionsinistres.repository.SinistreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class SinistreService {

    private final SinistreRepository sinistreRepository;
    private final FileStorageService fileStorageService;
    private static final Logger log = LoggerFactory.getLogger(SinistreService.class);

    @Autowired
    public SinistreService(SinistreRepository sinistreRepository, FileStorageService fileStorageService) {
        this.sinistreRepository = sinistreRepository;
        this.fileStorageService = fileStorageService;
    }

    private String logAndGetUserId(String description, LocalDate dateDeclaration) {
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

    private String handleFileUpload(MultipartFile file, String label, String directory) throws IOException {
        if (file != null && !file.isEmpty()) {
            log.info("Fichier {}: {}", label, file.getOriginalFilename());
            String filePath = fileStorageService.storeFile(file, directory);
            log.info("Fichier stocké: {}", filePath);
            return filePath;
        } else {
            log.warn("Aucun fichier {} fourni.", label);
            return null;
        }
    }

    public AccidentRouteSinistre declarerAccident(AccidentRouteSinistreDTO dto) throws IOException {
        String userId = logAndGetUserId(dto.getDescription(), dto.getDateDeclaration());
        String filePath = handleFileUpload(dto.getConstat(), "constat", "/app/uploads/constats");
        AccidentRouteSinistre sinistre = (filePath != null)
                ? AccidentRouteSinistreMapper.toEntity(dto, filePath)
                : AccidentRouteSinistreMapper.toEntity(dto);
        sinistre.setUserId(userId);
        return saveSinistre(sinistre);
    }

    public VolOuPerteObjetSinistre declarerVolOuPerteObjet(VolOuPerteObjetSinistreDTO dto) throws IOException {
        String userId = logAndGetUserId(dto.getDescription(), dto.getDateDeclaration());
        String filePath = handleFileUpload(dto.getDeclarationPolice(), "declarationPolice", "/app/uploads/declarationPolices");

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

    public IncidentMedicalSinistre declarerIncidentMedical(IncidentMedicalSinistreDTO dto) throws IOException {
        String userId = logAndGetUserId(dto.getDescription(), dto.getDateDeclaration());
        String filePath = handleFileUpload(dto.getDossierMedical(), "dossierMedical", "/app/uploads/dossierMedicaux");
        IncidentMedicalSinistre sinistre = (filePath != null)
                ? IncidentMedicalSinistreMapper.toEntity(dto, filePath)
                : IncidentMedicalSinistreMapper.toEntity(dto);
        sinistre.setUserId(userId);
        return saveSinistre(sinistre);
    }

    public ProblemeHebergementSinistre declarerProblemeHebergement(ProblemeHebergementSinistreDTO dto) throws IOException {
        String userId = logAndGetUserId(dto.getDescription(), dto.getDateDeclaration());
        ProblemeHebergementSinistre sinistre = ProblemeHebergementSinistreMapper.toEntity(dto);
        sinistre.setUserId(userId);
        return saveSinistre(sinistre);
    }

    public List<Sinistre> getSinistresUtilisateurConnecte() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Récupération des sinistres pour l'utilisateur: {}", userId);
        return sinistreRepository.findByUserId(userId);
    }

    public List<Sinistre> getSinistresAtraiter() {
        return sinistreRepository.findByStatut(StatutSinistre.EN_ATTENTE);
    }

    public Sinistre getSinistreById(Long id) {
        return sinistreRepository.findById(id).orElse(null);
    }

    public Sinistre updateSinistre(Sinistre sinistre) {
        return sinistreRepository.save(sinistre);
    }

    public void deleteSinistre(Long id) {
        sinistreRepository.deleteById(id);
    }

    public List<Sinistre> getAllSinistres() {
        return sinistreRepository.findAll();
    }

    public List<Sinistre> getSinistresByStatut(StatutSinistre statut) {
        return sinistreRepository.findByStatut(statut);
    }

    public List<Sinistre> getSinistresByType(TypeSinistre type) {
        return sinistreRepository.findByType(type);
    }

    public List<Sinistre> getSinistresByDateDeclaration(LocalDate dateDeclaration) {
        return sinistreRepository.findByDateDeclaration(dateDeclaration);
    }

    public List<Sinistre> getSinistresByUserId(String userId) {
        return sinistreRepository.findByUserId(userId);
    }

    public Sinistre traiterSinistre(Long id) {
        Sinistre sinistre = sinistreRepository.findById(id).orElse(null);
        if (sinistre != null) {
            sinistre.setStatut(StatutSinistre.EN_COURS);
            return sinistreRepository.save(sinistre);
        }
        return null;
    }

    public Sinistre cloturerSinistre(Long id) {
        Sinistre sinistre = sinistreRepository.findById(id).orElse(null);
        if (sinistre != null) {
            sinistre.setStatut(StatutSinistre.CLOTURE);
            return sinistreRepository.save(sinistre);
        }
        return null;
    }

    public Sinistre reouvrirSinistre(Long id) {
        Sinistre sinistre = sinistreRepository.findById(id).orElse(null);
        if (sinistre != null) {
            sinistre.setStatut(StatutSinistre.REOUVERT);
            return sinistreRepository.save(sinistre);
        }
        return null;
    }

    public Sinistre updateSinistreStatut(Long id, StatutSinistre statut) {
        Sinistre sinistre = sinistreRepository.findById(id).orElse(null);
        if (sinistre != null) {
            sinistre.setStatut(statut);
            return sinistreRepository.save(sinistre);
        }
        return null;
    }

    /*
    public AffectationAssistance affecterSinistre(Long sinistreId, String partenaireId) {
        AffectationAssistance affectation = new AffectationAssistance();
        affectation.setSinistreId(sinistreId);
        affectation.setPartenaireId(partenaireId);
        affectation.setDateAffectation(LocalDate.now());
        affectation.setStatut(StatutSinistre.EN_COURS);
        return affectationSinistreRepository.save(affectation);
    }
    */
}
