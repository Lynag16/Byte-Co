package com.glop.authentification.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.glop.authentification.dto.PartenaireDTO;
import com.glop.authentification.entities.Partenaire;
import com.glop.authentification.services.PartenaireService;

import java.util.Map;

@RestController
@RequestMapping("/api/partenaires")
public class PartenaireController {

    @Autowired
    private PartenaireService partenaireService;

    // Endpoint pour enregistrer un nouveau partenaire
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Partenaire partenaire) {
        try {
            // Appel au service pour enregistrer le partenaire et récupérer le DTO
            PartenaireDTO savedPartenaire = partenaireService.registerPartenaire(partenaire);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Partenaire inscrit avec succès, ID: " + savedPartenaire.getIdPartenaire());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de l'inscription : " + e.getMessage());
        }
    }

    // Endpoint pour authentifier un partenaire
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginDetails) {
        String emailPartenaire = loginDetails.get("emailPartenaire");
        String motdepassePartenaire = loginDetails.get("motdepassePartenaire");

        try {
            PartenaireDTO authenticatedPartenaire = partenaireService.authenticatePartenaireAndGetPartenaire(emailPartenaire, motdepassePartenaire);
            if (authenticatedPartenaire != null) {
                return ResponseEntity.ok(authenticatedPartenaire); // Retourne le PartenaireDTO en cas de succès
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Échec de la connexion : identifiants invalides");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'authentification : " + e.getMessage());
        }
    }

    // Endpoint pour réinitialiser le mot de passe
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> resetDetails) {
        String email = resetDetails.get("email");
        String newPassword = resetDetails.get("newPassword");

        boolean isReset = partenaireService.resetPassword(email, newPassword);
        if (isReset) {
            return ResponseEntity.ok("Mot de passe réinitialisé avec succès");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Échec de la réinitialisation du mot de passe");
    }
}
