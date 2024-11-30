package com.glop.authentification.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glop.authentification.entities.Client;
import com.glop.authentification.repositories.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Injection via l'interface

    // Méthode pour enregistrer un nouveau client
    public Client registerClient(Client client) {
        // Hashage du mot de passe
        client.setMotdepasse(passwordEncoder.encode(client.getMotdepasse()));
        client.setDateInscription(new Date()); // Date d'inscription actuelle
        return clientRepository.save(client);
    }

    // Méthode pour authentifier un client en fonction de l'email et du mot de passe
    public boolean authenticateClient(String email, String motdepasse) {
        Client client = clientRepository.findByEmail(email);
        if (client != null && passwordEncoder.matches(motdepasse, client.getMotdepasse())) {
            return true; // Connexion réussie
        }
        return false; // Échec de la connexion
    }
}
