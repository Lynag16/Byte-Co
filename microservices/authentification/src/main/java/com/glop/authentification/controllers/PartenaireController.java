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
        return ResponseEntity.ok("Partanire inscrit avec succès, ID: " + savedPartenaire.getIdPartenaire());
    }

    // Endpoint pour authentifier un client
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginDetails) {
        String emailPartenaire = loginDetails.get("emailPartenaire");
        String motdepassePartenaire = loginDetails.get("motdepassePartenaire");

        boolean isAuthenticated = partenaireService.authenticatepartenaire(emailPartenaire, motdepassePartenaire);
        if (isAuthenticated) {
            return ResponseEntity.ok("Connexion réussie");
        }
        return ResponseEntity.status(401).body("Échec de la connexion");
    }

}
