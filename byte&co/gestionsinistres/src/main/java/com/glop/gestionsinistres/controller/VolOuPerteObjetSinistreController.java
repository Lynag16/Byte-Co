package com.glop.gestionsinistres.controller;

import com.glop.gestionsinistres.model.AccidentRouteSinistre;
import com.glop.gestionsinistres.service.SinistreDeclarationService;
import com.glop.gestionsinistres.dto.VolOuPerteObjetSinistreDTO;
import com.glop.gestionsinistres.model.VolOuPerteObjetSinistre;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/sinistres/vol-ou-perte-objet")
public class VolOuPerteObjetSinistreController {

    private final SinistreDeclarationService declarationService;

    public VolOuPerteObjetSinistreController(SinistreDeclarationService declarationService) {
        this.declarationService = declarationService;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    @PreAuthorize("hasAnyRole('USER', 'CLIENT')")
    public ResponseEntity<VolOuPerteObjetSinistre> declarerVol(
            @RequestPart("dto") VolOuPerteObjetSinistreDTO dto,
            @RequestPart(value = "declarationPolice", required = false) MultipartFile declarationPolice
    ) throws IOException {
        dto.setDeclarationPolice(declarationPolice);
        VolOuPerteObjetSinistre created = declarationService.declarerVol(dto);
        return ResponseEntity.ok(created);
    }

    @Value("${app.upload.dir.declarationPolices}")
    private String uploadDir;

    @GetMapping("/uploads/declarationPolices/{filename:.+}")
    public ResponseEntity<?> downloadDeclarationPolice(@PathVariable String filename) throws IOException {
        Path file = Paths.get(uploadDir, filename).normalize().toAbsolutePath();
        System.out.println(">>> Tentative d'accès à : " + file);

        if (!Files.exists(file)) {
            System.out.println(">>> Fichier introuvable !");
            return ResponseEntity.notFound().build();
        }

        InputStream inputStream = Files.newInputStream(file);
        InputStreamResource resource = new InputStreamResource(inputStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }
}
