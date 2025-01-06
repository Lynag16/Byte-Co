package com.glop.authentification.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.glop.authentification.dto.ClientDTO;
import com.glop.authentification.services.ClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Endpoint pour enregistrer un nouveau client
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody ClientDTO clientDTO) {
        ClientDTO savedClient = clientService.registerClient(clientDTO);
        return ResponseEntity.ok("Client inscrit avec succès, ID: " + savedClient.getIdClient());
    }

    // Endpoint pour authentifier un client
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginDetails) {
        String email = loginDetails.get("email");
        String motdepasse = loginDetails.get("motdepasse");

        ClientDTO authenticatedClient = clientService.authenticateClientAndGetClient(email, motdepasse);
        if (authenticatedClient != null) {
            return ResponseEntity.ok(authenticatedClient);
        }
        return ResponseEntity.status(401).body("Échec de la connexion");
    }

    // Endpoint pour réinitialiser le mot de passe
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> resetDetails) {
        String email = resetDetails.get("email");
        String newPassword = resetDetails.get("newPassword");

        boolean isReset = clientService.resetPassword(email, newPassword);
        if (isReset) {
            return ResponseEntity.ok("Mot de passe réinitialisé avec succès");
        }
        return ResponseEntity.status(400).body("Échec de la réinitialisation du mot de passe");
    }
}
