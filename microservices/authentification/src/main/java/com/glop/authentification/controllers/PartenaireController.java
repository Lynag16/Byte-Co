package com.glop.authentification.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glop.authentification.entities.Partenaire;
import com.glop.authentification.services.PartenaireService;

@RestController
@RequestMapping("/api/partenaires")
public class PartenaireController {

    @Autowired
    private PartenaireService partenaireService;

    // Endpoint pour enregistrer un nouveau partenaire
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Partenaire partenaire) {
        Partenaire savedPartenaire = partenaireService.registerpartenaire(partenaire);
        return ResponseEntity.ok("Partenaire inscrit avec succès, ID: " + savedPartenaire.getIdPartenaire());
    }

    // Endpoint pour authentifier un client
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginDetails) {
        String emailPartenaire = loginDetails.get("emailPartenaire");
        String motdepassePartenaire = loginDetails.get("motdepassePartenaire");

        Partenaire authenticatedPartenaire = partenaireService.authenticatePartenaireAndGetPartenaire(emailPartenaire, motdepassePartenaire);
        if (authenticatedPartenaire != null) {
            return ResponseEntity.ok(authenticatedPartenaire); // Retourne l'objet Partenaire si authentification réussie
        }
        return ResponseEntity.status(401).body("Échec de la connexion");
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
        return ResponseEntity.status(400).body("Échec de la réinitialisation du mot de passe");
    }
}
