package com.glop.authentification.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.glop.authentification.entities.Client;
import com.glop.authentification.repositories.ClientRepository;

import com.glop.authentification.dto.ClientDTO;
import com.glop.authentification.mappers.ClientMapper;

import java.util.Date;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Méthode pour enregistrer un nouveau client
//    public ClientDTO registerClient(ClientDTO clientDTO) {
//        Client client = ClientMapper.toEntity(clientDTO);
//        client.setDateInscription(new Date()); // Date d'inscription actuelle
//        client.setMotdepasse(client.getMotdepasse()); // Mot de passe en clair
//        Client savedClient = clientRepository.save(client);
//        return ClientMapper.toDTO(savedClient);
//    }

    public ClientDTO registerClient(ClientDTO clientDTO) {
        // Convertir le DTO en entité Client
        Client client = ClientMapper.toEntity(clientDTO);
        
        // Assurez-vous de définir correctement le mot de passe à partir du DTO
        client.setMotdepasse(clientDTO.getMotdepasse()); // Définir le mot de passe du DTO
        
        // Définir la date d'inscription
        client.setDateInscription(new Date()); // Date d'inscription actuelle
        
        // Enregistrer le client dans la base de données
        Client savedClient = clientRepository.save(client);
        
        // Retourner le ClientDTO du client enregistré
        return ClientMapper.toDTO(savedClient);
    }

    
    // Méthode pour authentifier un client et retourner l'objet ClientDTO en cas de succès
    public ClientDTO authenticateClientAndGetClient(String email, String motdepasse) {
        Client client = clientRepository.findByEmail(email);
        if (client != null) {
            System.out.println("Email trouvé : " + email);
            System.out.println("Mot de passe envoyé : " + motdepasse);
            System.out.println("Mot de passe en base : " + client.getMotdepasse());
            
            if (motdepasse.equals(client.getMotdepasse())) {
                return ClientMapper.toDTO(client);
            }
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
}
