package com.glop.gestionsinistres.service;

import com.glop.gestionsinistres.dto.assistance.DemandeDepannageDTO;
import com.glop.gestionsinistres.model.assistance.DemandeDepannage;
import com.glop.gestionsinistres.model.assistance.StatutDemande;
import com.glop.gestionsinistres.repository.DemandeDepannageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.glop.gestionsinistres.mapper.assistance.DemandeDepannageMapper;

import java.util.List;

@Service
public class DemandeDepannageService {

    private final DemandeDepannageRepository repository;
    private static final Logger log = LoggerFactory.getLogger(DemandeDepannageService.class);

    public DemandeDepannageService(DemandeDepannageRepository repository) {
        this.repository = repository;
    }

    private String logAndGetUserId(String description) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Demande de dépannage par utilisateur: {}", userId);
        log.info("Description: {}", description);
        return userId;
    }

    private DemandeDepannage saveDemandeDepannage(DemandeDepannage demandeDepannage) {
        DemandeDepannage saved = repository.save(demandeDepannage);
        log.info("Demande de dépannage enregistrée ID: {}", saved.getId());
        return saved;
    }

    public DemandeDepannage declarerDemandeDepannage(DemandeDepannageDTO dto) {
        String userId = logAndGetUserId(dto.getCategorieProbleme());

        DemandeDepannage demandeDepannage = DemandeDepannageMapper.toEntity(dto);
        demandeDepannage.setUserId(userId);
        return saveDemandeDepannage(demandeDepannage);
    }

    public List<DemandeDepannage> getDemandesDepannageUtilisateurConnecte() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Récupération des demandes de dépannage pour l'utilisateur: {}", userId);
        return repository.findByUserId(userId);
    }

    public List<DemandeDepannage> getDemandesAtraiter() {
        return repository.findByStatut(StatutDemande.EN_ATTENTE);
    }



}
