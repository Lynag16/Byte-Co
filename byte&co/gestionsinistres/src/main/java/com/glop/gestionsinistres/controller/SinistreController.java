package com.glop.gestionsinistres.controller;

import com.glop.gestionsinistres.dto.SinistreDTO;
import com.glop.gestionsinistres.service.SinistreService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sinistres")
public class SinistreController {

    private final SinistreService sinistreService;

    public SinistreController(SinistreService sinistreService) {
        this.sinistreService = sinistreService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<List<SinistreDTO>> getMesSinistres(HttpServletRequest request) {
        String userId = request.getUserPrincipal().getName(); // extrait depuis le token
        List<SinistreDTO> mesSinistres = sinistreService.getSinistresByUserId(userId);
        return ResponseEntity.ok(mesSinistres);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<SinistreDTO> createSinistre(@RequestBody SinistreDTO dto, HttpServletRequest request) {
        String userId = request.getUserPrincipal().getName();
        dto.setUserId(userId);
        return ResponseEntity.ok(sinistreService.createSinistre(dto));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<SinistreDTO> getSinistre(@PathVariable Long id) {
        return sinistreService.getSinistreById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<SinistreDTO> updateSinistre(@PathVariable Long id, @RequestBody SinistreDTO dto) {
        return sinistreService.updateSinistre(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<Void> deleteSinistre(@PathVariable Long id) {
        sinistreService.deleteSinistre(id);
        return ResponseEntity.noContent().build();
    }
}
