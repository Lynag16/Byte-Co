package com.glop.authentification.mappers;

import com.glop.authentification.entities.Client;
import com.glop.authentification.dto.ClientDTO;

public class ClientMapper {

    public static ClientDTO toDTO(Client client) {
        if (client == null) {
            return null;
        }
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setIdClient(client.getIdClient());
        clientDTO.setNom(client.getNom());
        clientDTO.setPrenom(client.getPrenom());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setTelephone(client.getTelephone());
        clientDTO.setLanguePreference(client.getLanguePreference());
        clientDTO.setMonnaiePreference(client.getMonnaiePreference());
        clientDTO.setAdresseclient(client.getAdresseclient());
        clientDTO.setBadge(client.getBadge());
        clientDTO.setStatut(client.getStatut());
        clientDTO.setMotdepasse(client.getMotdepasse()); // Add this line to map the motdepasse
        return clientDTO;
    }

    public static Client toEntity(ClientDTO clientDTO) {
        if (clientDTO == null) {
            return null;
        }
        Client client = new Client();
        client.setIdClient(clientDTO.getIdClient());
        client.setNom(clientDTO.getNom());
        client.setPrenom(clientDTO.getPrenom());
        client.setEmail(clientDTO.getEmail());
        client.setTelephone(clientDTO.getTelephone());
        client.setLanguePreference(clientDTO.getLanguePreference());
        client.setMonnaiePreference(clientDTO.getMonnaiePreference());
        client.setAdresseclient(clientDTO.getAdresseclient());
        client.setBadge(clientDTO.getBadge());
        client.setStatut(clientDTO.getStatut());
        client.setMotdepasse(clientDTO.getMotdepasse()); // Add this line to map the motdepasse
        return client;
    }
}
