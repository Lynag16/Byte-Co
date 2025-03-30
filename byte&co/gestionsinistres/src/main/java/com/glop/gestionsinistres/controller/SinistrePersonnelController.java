package com.glop.gestionsinistres.controller;

import com.glop.gestionsinistres.model.sinistre.AffectationSinistre;
import com.glop.gestionsinistres.model.sinistre.Sinistre;
import com.glop.gestionsinistres.service.SinistreService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personnel/sinistres")
@CrossOrigin(origins = "http://localhost:3000")
public class SinistrePersonnelController {

    private final SinistreService sinistreService;

    public SinistrePersonnelController(SinistreService sinistreService) {
        this.sinistreService = sinistreService;
    }

    @GetMapping
    @PreAuthorize("hasRole('GESTIONNAIRE')")
    public ResponseEntity<List<Sinistre>> getAllSinistres() {
        System.out.println("✅ Accès autorisé pour PERSONNEL");
        return ResponseEntity.ok(sinistreService.getAllSinistres());
    }

    @PostMapping("/{id}/traiter")
    @PreAuthorize("hasRole('GESTIONNAIRE')")
    public ResponseEntity<Sinistre> traiter(@PathVariable Long id) {
        return ResponseEntity.ok(sinistreService.traiterSinistre(id));
    }

    @PostMapping("/{id}/cloturer")
    @PreAuthorize("hasRole('GESTIONNAIRE')")
    public ResponseEntity<Sinistre> cloturer(@PathVariable Long id) {
        return ResponseEntity.ok(sinistreService.cloturerSinistre(id));
    }

    @PostMapping("/{id}/reouvrir")
    @PreAuthorize("hasRole('GESTIONNAIRE')")
    public ResponseEntity<Sinistre> reouvrir(@PathVariable Long id) {
        return ResponseEntity.ok(sinistreService.reouvrirSinistre(id));
    }

    @PostMapping("/{id}/affecter")
    @PreAuthorize("hasRole('GESTIONNAIRE')")
    public ResponseEntity<AffectationSinistre> affecter(@PathVariable Long id, @RequestParam String partenaireId) {
        return ResponseEntity.ok(sinistreService.affecterSinistre(id, partenaireId));
    }
}
