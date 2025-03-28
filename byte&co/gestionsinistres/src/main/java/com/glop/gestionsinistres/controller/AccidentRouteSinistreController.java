package com.glop.gestionsinistres.controller;

import com.glop.gestionsinistres.dto.AccidentRouteSinistreDTO;
import com.glop.gestionsinistres.model.AccidentRouteSinistre;
import com.glop.gestionsinistres.service.SinistreDeclarationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import org.springframework.core.io.InputStreamResource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/sinistres/accident")
public class AccidentRouteSinistreController {

    private final SinistreDeclarationService declarationService;

    public AccidentRouteSinistreController(SinistreDeclarationService declarationService) {
        this.declarationService = declarationService;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    @PreAuthorize("hasAnyRole('USER', 'CLIENT')")
    public ResponseEntity<AccidentRouteSinistre> declarerAccident(
            @RequestPart("dto") AccidentRouteSinistreDTO dto,
            @RequestPart(value = "constat", required = false) MultipartFile constat
    ) throws IOException {
        dto.setConstat(constat);
        AccidentRouteSinistre created = declarationService.declarerAccident(dto);
        return ResponseEntity.ok(created);
    }

    @Value("${app.upload.dir}")
    private String uploadDir;

    @GetMapping("/uploads/constats/{filename:.+}")
    public ResponseEntity<?> downloadConstat(@PathVariable String filename) throws IOException {
        Path file = Paths.get(uploadDir, filename).normalize().toAbsolutePath();
        System.out.println(">>> Tentative d'accès à : " + file);

        if (!Files.exists(file)) {
            System.out.println(">>> Fichier introuvable !");
            return ResponseEntity.notFound().build();
        }

        InputStreamResource resource = new InputStreamResource(Files.newInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                .body(resource);
    }

}
