package com.glop.gestionclient.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glop.gestionclient.entities.Client;
import com.glop.gestionclient.repositories.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Client creerClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Client updateClient(Long id, Client clientDetails) {
        return clientRepository.findById(id)
                .map(client -> {
                    client.setNom(clientDetails.getNom());
                    client.setPrenom(clientDetails.getPrenom());
                    client.setEmail(clientDetails.getEmail());
                    client.setTelephone(clientDetails.getTelephone());
                    client.setAdresse(clientDetails.getAdresse());
                    return clientRepository.save(client);
                })
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}