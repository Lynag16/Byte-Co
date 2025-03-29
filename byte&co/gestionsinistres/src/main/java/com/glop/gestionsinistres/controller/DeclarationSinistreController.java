package com.glop.gestionsinistres.controller;

import com.glop.gestionsinistres.dto.*;
import com.glop.gestionsinistres.model.*;
import com.glop.gestionsinistres.service.SinistreService;
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
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/sinistres")
public class DeclarationSinistreController {

    private final SinistreService declarationService;

    @Value("${app.upload.dir.constats}")
    private String uploadConstatDir;

    @Value("${app.upload.dir.declarationPolices}")
    private String uploadDeclarationPoliceDir;

    @Value("${app.upload.dir.dossierMedicaux}")
    private String uploadDossierMedicalDir;

    public DeclarationSinistreController(SinistreService declarationService) {
        this.declarationService = declarationService;
    }

    // Accident de route
    @PostMapping("/accident")
    @PreAuthorize("hasAnyRole('USER', 'CLIENT')")
    public ResponseEntity<AccidentRouteSinistre> declarerAccident(
            @RequestPart("dto") AccidentRouteSinistreDTO dto,
            @RequestPart(value = "constat", required = false) MultipartFile constat
    ) throws IOException {
        dto.setConstat(constat);
        AccidentRouteSinistre created = declarationService.declarerAccident(dto);
        return ResponseEntity.ok(created);
    }

    // Vol ou Perte d'objet
    @PostMapping("/vol-ou-perte-objet")
    @PreAuthorize("hasAnyRole('USER', 'CLIENT')")
    public ResponseEntity<VolOuPerteObjetSinistre> declarerVolOuPerteObjet(
            @RequestPart("dto") VolOuPerteObjetSinistreDTO dto,
            @RequestPart(value = "declarationPolice", required = false) MultipartFile declarationPolice
    ) throws IOException {
        dto.setDeclarationPolice(declarationPolice);
        VolOuPerteObjetSinistre created = declarationService.declarerVolOuPerteObjet(dto);
        return ResponseEntity.ok(created);
    }

    // Retard transport
    @PostMapping("/retard-transport")
    @PreAuthorize("hasAnyRole('USER', 'CLIENT')")
    public ResponseEntity<RetardTransportSinistre> declarerRetardTransport(
            @RequestPart("dto") RetardTransportSinistreDTO dto
    ) {
        RetardTransportSinistre created = declarationService.declarerRetardTransport(dto);
        return ResponseEntity.ok(created);
    }

    // Incident médical
    @PostMapping("/incident-medical")
    @PreAuthorize("hasAnyRole('USER', 'CLIENT')")
    public ResponseEntity<IncidentMedicalSinistre> declarerIncidentMedical(
            @RequestPart("dto") IncidentMedicalSinistreDTO dto,
            @RequestPart(value = "dossierMedical", required = false) MultipartFile dossierMedical
    ) throws IOException {
        dto.setDossierMedical(dossierMedical);
        IncidentMedicalSinistre created = declarationService.declarerIncidentMedical(dto);
        return ResponseEntity.ok(created);
    }

    // Problème d'hébergement
    @PostMapping("/probleme-hebergement")
    @PreAuthorize("hasAnyRole('USER', 'CLIENT')")
    public ResponseEntity<ProblemeHebergementSinistre> declarerProblemeHebergement(
            @RequestPart("dto") ProblemeHebergementSinistreDTO dto
    ) throws IOException {
        ProblemeHebergementSinistre created = declarationService.declarerProblemeHebergement(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/uploads/constats/{filename:.+}")
    public ResponseEntity<?> displayConstat(@PathVariable String filename) throws IOException {
        Path file = Paths.get(uploadConstatDir, filename).normalize().toAbsolutePath();
        System.out.println(">>> Tentative d'accès à : " + file);

        if (!Files.exists(file)) {
            System.out.println(">>> Fichier introuvable !");
            return ResponseEntity.notFound().build();
        }

        System.out.println(">>> Fichier trouvé, préparation pour affichage...");
        InputStream inputStream = Files.newInputStream(file);
        InputStreamResource resource = new InputStreamResource(inputStream);

        // Changez l'en-tête pour que le fichier soit affiché dans le navigateur
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")  // Spécifie que c'est un PDF
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")  // Utilise "inline" pour afficher dans le navigateur
                .body(resource);
    }

    // Téléchargement et affichage du fichier déclaration de police pour un vol ou perte d'objet
    @GetMapping("/uploads/declarationPolices/{filename:.+}")
    public ResponseEntity<?> displayDeclarationPolice(@PathVariable String filename) throws IOException {
        Path file = Paths.get(uploadDeclarationPoliceDir, filename).normalize().toAbsolutePath();
        System.out.println(">>> Tentative d'accès à : " + file);

        if (!Files.exists(file)) {
            System.out.println(">>> Fichier introuvable !");
            return ResponseEntity.notFound().build();
        }

        System.out.println(">>> Fichier trouvé, préparation pour affichage...");
        InputStream inputStream = Files.newInputStream(file);
        InputStreamResource resource = new InputStreamResource(inputStream);

        // Changez l'en-tête pour afficher le fichier dans le navigateur si inline, ou pour téléchargement si attachment
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")  // Spécifie que c'est un PDF
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")  // Affichage inline dans le navigateur
                .body(resource);
    }

    // Téléchargement et affichage du fichier dossier médical pour un incident médical
    @GetMapping("/uploads/dossierMedicaux/{filename:.+}")
    public ResponseEntity<?> displayDossierMedical(@PathVariable String filename) throws IOException {
        Path file = Paths.get(uploadDossierMedicalDir, filename).normalize().toAbsolutePath();
        System.out.println(">>> Tentative d'accès à : " + file);

        if (!Files.exists(file)) {
            System.out.println(">>> Fichier introuvable !");
            return ResponseEntity.notFound().build();
        }

        System.out.println(">>> Fichier trouvé, préparation pour affichage...");
        InputStream inputStream = Files.newInputStream(file);
        InputStreamResource resource = new InputStreamResource(inputStream);

        // Changez l'en-tête pour afficher le fichier dans le navigateur si inline, ou pour téléchargement si attachment
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")  // Spécifie que c'est un PDF
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")  // Affichage inline dans le navigateur
                .body(resource);
    }


}
