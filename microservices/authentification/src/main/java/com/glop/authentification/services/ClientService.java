package com.glop.authentification.services;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.glop.authentification.entities.Client;
import com.glop.authentification.repositories.ClientRepository;

import com.glop.authentification.dto.ClientDTO;
import com.glop.authentification.mappers.ClientMapper;

import java.util.Date;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    // Méthode pour enregistrer un nouveau client
//    public ClientDTO registerClient(ClientDTO clientDTO) {
//        Client client = ClientMapper.toEntity(clientDTO);
//        client.setDateInscription(new Date()); // Date d'inscription actuelle
//        client.setMotdepasse(client.getMotdepasse()); // Mot de passe en clair
//        Client savedClient = clientRepository.save(client);
//        return ClientMapper.toDTO(savedClient);
//    }

@Transactional
public ClientDTO registerClient(ClientDTO clientDTO) {
    logger.info("Attempting to register client with email: {}", clientDTO.getEmail());

    // Convertir le DTO en entité Client
    Client client = ClientMapper.toEntity(clientDTO);
    
    // Log the client fields
    logger.info("Client Data: nom={}, prenom={}, email={}, telephone={}, motdepasse={}",
            client.getNom(), client.getPrenom(), client.getEmail(), client.getTelephone(), client.getMotdepasse());

    client.setMotdepasse(clientDTO.getMotdepasse()); // Set the password
    client.setDateInscription(new Date()); // Set the registration date

    // Ensure no null values are present where they shouldn't be
    if (client.getNom() == null || client.getEmail() == null || client.getMotdepasse() == null) {
        logger.error("Required fields are missing in client data.");
        throw new IllegalArgumentException("Required fields are missing.");
    }

    Client savedClient = clientRepository.save(client);

    // Log saved client
    logger.info("Saved client with ID: {}", savedClient.getIdClient());

    return ClientMapper.toDTO(savedClient);
}


    
    // Méthode pour authentifier un client et retourner l'objet ClientDTO en cas de succès
    public ClientDTO authenticateClientAndGetClient(String email, String motdepasse) {
        Client client = clientRepository.findByEmail(email);
        if (client != null) {
            System.out.println("Email trouvé : " + email);
            System.out.println("Mot de passe envoyé : " + motdepasse);
            System.out.println("Mot de passe en base : " + client.getMotdepasse());
            
            // Check if the password matches (plain text comparison)
            if (motdepasse != null && motdepasse.equals(client.getMotdepasse())) {
                System.out.println("Mot de passe valide.");
                return ClientMapper.toDTO(client);
            } else {
                System.out.println("Mot de passe invalide.");
            }
        } else {
            System.out.println("Client non trouvé pour l'email : " + email);
        }
        return null;
    }
    


    // Méthode pour réinitialiser le mot de passe d'un client
    public boolean resetPassword(String email, String newPassword) {
        Client client = clientRepository.findByEmail(email);
        if (client != null) {
            client.setMotdepasse(newPassword); // Nouveau mot de passe en clair
            clientRepository.save(client); // Sauvegarde le client avec le nouveau mot de passe
            return true;
        }
        return false;
    }

    public boolean clientExists(String email) {
        return clientRepository.existsByEmail(email);  // Assuming clientRepository is your JPA repository
    }
    
}
