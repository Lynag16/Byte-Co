package com.glop.gestionutilisateurs.services;

import com.glop.gestionutilisateurs.entities.Client;
import com.glop.gestionutilisateurs.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(int id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(int id, Client client) {
        Client existingClient = clientRepository.findById(id).orElse(null);
        if (existingClient != null) {
            existingClient.setNom(client.getNom());
            existingClient.setPrenom(client.getPrenom());
            existingClient.setEmail(client.getEmail());
            existingClient.setTelephone(client.getTelephone());
            existingClient.setMotdepasse(client.getMotdepasse());
            existingClient.setDateInscription(client.getDateInscription());
            existingClient.setLanguePreference(client.getLanguePreference());
            existingClient.setMonnaiePreference(client.getMonnaiePreference());
            existingClient.setAdresseclient(client.getAdresseclient());
            existingClient.setBadge(client.getBadge());
            existingClient.setStatut(client.getStatut());
            return clientRepository.save(existingClient);
        }
        return null;
    }

    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }
}
