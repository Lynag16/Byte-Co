package com.glop.empreintecarbone;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class EmpreinteCarboneService {

    private final RestTemplate restTemplate;

    // Remplacez par votre clé d'API obtenue
    private static final String API_KEY = "d75181cd-2265-4ee4-8c3f-35785292f5f2";    


    public EmpreinteCarboneService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getCarbonFootprint(int km, String transport) {
        String url = "https://impactco2.fr/api/v1/transport";

        // Construire l'URL avec les paramètres
        String uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("km", km)
                .queryParam("displayAll", 0)
                .queryParam("transports", transport)
                .queryParam("ignoreRadiativeForcing", 0)
                .queryParam("occupencyRate", 1)
                .queryParam("includeConstruction", 0)
                .queryParam("language", "fr")
                .toUriString();

        // Ajouter les en-têtes avec la clé d'API
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Effectuer la requête GET avec les en-têtes
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class, entity);

        return response.getBody();
    }
}