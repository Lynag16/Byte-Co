package com.glop.gestionutilisateurs.services;

import com.glop.gestionutilisateurs.dtos.ClientDTO;
import com.glop.gestionutilisateurs.entities.Client;
import com.glop.gestionutilisateurs.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Convertir Client en ClientDTO
    private ClientDTO convertToDTO(Client client) {
        return new ClientDTO(
            client.getNom(), 
            client.getPrenom(), 
            client.getEmail(), 
            client.getTelephone(),
            client.getMotdepasse(),
            client.getDateInscription(),
            client.getLanguePreference(),
            client.getMonnaiePreference(),
            client.getAdresseclient(),
            //client.getBadge(),
            client.getStatut()
        );
    }

    // Convertir ClientDTO en Client
    private Client convertToEntity(ClientDTO clientDTO) {
        return new Client(
            clientDTO.getNom(),
            clientDTO.getPrenom(),
            clientDTO.getEmail(),
            clientDTO.getTelephone(),
            clientDTO.getMotdepasse(),
            clientDTO.getDateInscription(),
            clientDTO.getLanguePreference(),
            clientDTO.getMonnaiePreference(),
            clientDTO.getAdresseclient(),
            //clientDTO.getBadge(),
            clientDTO.getStatut()
        );
    }

    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                      .map(this::convertToDTO)
                      .toList();
    }

    public ClientDTO getClientById(int id) {
        Client client = clientRepository.findById(id).orElse(null);
        return client != null ? convertToDTO(client) : null;
    }

    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = convertToEntity(clientDTO);
        Client savedClient = clientRepository.save(client);
        return convertToDTO(savedClient);
    }

    public ClientDTO updateClient(int id, ClientDTO clientDTO) {
        Optional<Client> existingClient = clientRepository.findById(id);
        if (existingClient.isPresent()) {
            Client client = existingClient.get();
            client.setNom(clientDTO.getNom());
            client.setPrenom(clientDTO.getPrenom());
            client.setEmail(clientDTO.getEmail());
            client.setTelephone(clientDTO.getTelephone());
            client.setMotdepasse(clientDTO.getMotdepasse());
            client.setDateInscription(clientDTO.getDateInscription());
            client.setLanguePreference(clientDTO.getLanguePreference());
            client.setMonnaiePreference(clientDTO.getMonnaiePreference());
            client.setAdresseclient(clientDTO.getAdresseclient());
            //client.setBadge(clientDTO.getBadge());
            client.setStatut(clientDTO.getStatut());
            Client updatedClient = clientRepository.save(client);
            return convertToDTO(updatedClient);
        }
        return null;
    }

    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }
}
