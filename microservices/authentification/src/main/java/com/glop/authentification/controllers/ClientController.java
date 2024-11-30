package com.glop.authentification.controllers;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.glop.authentification.entities.Client;
import com.glop.authentification.services.ClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Endpoint pour enregistrer un nouveau client
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Client client) {
        Client savedClient = clientService.registerClient(client);
        return ResponseEntity.ok("Client inscrit avec succès, ID: " + savedClient.getIdClient());
    }

    // Endpoint pour authentifier un client
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginDetails) {
        String email = loginDetails.get("email");
        String motdepasse = loginDetails.get("motdepasse");

        boolean isAuthenticated = clientService.authenticateClient(email, motdepasse);
        if (isAuthenticated) {
            return ResponseEntity.ok("Connexion réussie");
        }
        return ResponseEntity.status(401).body("Échec de la connexion");
    }

}
