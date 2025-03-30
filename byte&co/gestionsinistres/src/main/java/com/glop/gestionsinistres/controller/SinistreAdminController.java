package com.glop.gestionsinistres.controller;

import com.glop.gestionsinistres.model.sinistre.AffectationSinistre;
import com.glop.gestionsinistres.model.sinistre.Sinistre;
import com.glop.gestionsinistres.service.SinistreService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/sinistres")
@CrossOrigin(origins = "http://localhost:3000")
public class SinistreAdminController {

    private final SinistreService sinistreService;

    public SinistreAdminController(SinistreService sinistreService) {
        this.sinistreService = sinistreService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Sinistre>> getAllSinistres() {
        System.out.println("✅ Accès autorisé pour ADMIN");
        return ResponseEntity.ok(sinistreService.getAllSinistres());
    }

    @PostMapping("/{id}/traiter")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Sinistre> traiter(@PathVariable Long id) {
        return ResponseEntity.ok(sinistreService.traiterSinistre(id));
    }

    @PostMapping("/{id}/cloturer")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Sinistre> cloturer(@PathVariable Long id) {
        return ResponseEntity.ok(sinistreService.cloturerSinistre(id));
    }

    @PostMapping("/{id}/reouvrir")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Sinistre> reouvrir(@PathVariable Long id) {
        return ResponseEntity.ok(sinistreService.reouvrirSinistre(id));
    }

    @PostMapping("/{id}/affecter")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AffectationSinistre> affecter(@PathVariable Long id, @RequestParam String partenaireId) {
        return ResponseEntity.ok(sinistreService.affecterSinistre(id, partenaireId));
    }
}
