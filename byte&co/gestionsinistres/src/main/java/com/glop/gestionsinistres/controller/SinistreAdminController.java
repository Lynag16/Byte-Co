package com.glop.gestionsinistres.controller;

import com.glop.gestionsinistres.dto.SinistreDTO;
import com.glop.gestionsinistres.service.SinistreService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/sinistres")
@PreAuthorize("hasAuthority('ADMIN')")
public class SinistreAdminController {

    private final SinistreService sinistreService;

    public SinistreAdminController(SinistreService sinistreService) {
        this.sinistreService = sinistreService;
    }

    @GetMapping
    public ResponseEntity<List<SinistreDTO>> getAllSinistres(@RequestParam(required = false) String statut) {
        List<SinistreDTO> result = (statut != null)
                ? sinistreService.getSinistresByStatut(statut)
                : sinistreService.getAllSinistres();

        return ResponseEntity.ok(result);
    }
}
