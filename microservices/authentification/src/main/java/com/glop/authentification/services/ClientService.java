package com.glop.authentification.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glop.authentification.entities.Client;
import com.glop.authentification.repositories.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Méthode pour enregistrer un nouveau client
    public Client registerClient(Client client) {
        client.setDateInscription(new Date()); // Date d'inscription actuelle
        return clientRepository.save(client);
    }

    // Méthode pour authentifier un client en fonction de l'email et du mot de passe
    public boolean authenticateClient(String email, String motdepasse) {
        Client client = clientRepository.findByEmail(email);
        if (client != null && motdepasse.equals(client.getMotdepasse())) {
            return true; // Connexion réussie
        }
        return false; // Échec de la connexion
    }

    // Méthode pour authentifier un client et retourner l'objet Client en cas de succès
    public Client authenticateClientAndGetClient(String email, String motdepasse) {
        Client client = clientRepository.findByEmail(email);
        if (client != null && motdepasse.equals(client.getMotdepasse())) {
            return client; // Retourne l'objet Client si l'authentification réussie
        }
        return null; // Retourne null si l'authentification échoue
    }
    
    // Méthode pour réinitialiser le mot de passe d'un client
    public boolean resetPassword(String email, String newPassword) {
        Client client = clientRepository.findByEmail(email);
        if (client != null) {
            client.setMotdepasse(newPassword); // Met à jour le mot de passe
            clientRepository.save(client); // Sauvegarde le client avec le nouveau mot de passe
            return true;
        }
        return false;
    }
}
