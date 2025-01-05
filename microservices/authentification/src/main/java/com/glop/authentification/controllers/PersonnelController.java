package com.glop.authentification.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glop.authentification.entities.Personnel;
import com.glop.authentification.services.PersonnelService;

@RestController
@RequestMapping("/api/personnels")
public class PersonnelController {

    @Autowired
    private PersonnelService personnelService;

    // Endpoint pour enregistrer un nouveau personnel
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Personnel personnel) {
        try {
            Personnel savedPersonnel = personnelService.registerPersonnel(personnel);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Personnel inscrit avec succès, ID: " + savedPersonnel.getIdpersonnel());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de l'inscription : " + e.getMessage());
        }
    }

    // Endpoint pour authentifier un personnel
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginDetails) {
        String emailpersonnel = loginDetails.get("emailpersonnel");
        String motdepassepersonnel = loginDetails.get("motdepassepersonnel");

        try {
            Personnel authenticatedPersonnel = personnelService.authenticatePersonnelAndGetPersonnel(emailpersonnel, motdepassepersonnel);
            if (authenticatedPersonnel != null) {
                return ResponseEntity.ok(authenticatedPersonnel);  // Retourne l'objet complet Personnel, y compris le mot de passe
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

        boolean isReset = personnelService.resetPassword(email, newPassword);
        if (isReset) {
            return ResponseEntity.ok("Mot de passe réinitialisé avec succès");
        }
        return ResponseEntity.status(400).body("Échec de la réinitialisation du mot de passe");
    }
}