package com.glop.gestionsinistres.controller;

import com.glop.gestionsinistres.model.sinistre.Sinistre;
import com.glop.gestionsinistres.service.SinistreService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/mes-sinistres")
public class MesSinistresController {

    private final SinistreService sinistreService;

    public MesSinistresController(SinistreService sinistreService) {
        this.sinistreService = sinistreService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'CLIENT')")
    public ResponseEntity<List<Sinistre>> getMesSinistres() {
        List<Sinistre> sinistres = sinistreService.getSinistresUtilisateurConnecte();
        return ResponseEntity.ok(sinistres);
    }
}