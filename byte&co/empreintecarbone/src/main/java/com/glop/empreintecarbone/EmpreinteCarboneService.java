package com.glop.empreintecarbone;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Value;




@Service
public class EmpreinteCarboneService {

    private final RestTemplate restTemplate;

    @Value("${impactco2.api.key}")
    private String API_KEY;

   
    //private static final String API_KEY = "";    


    public EmpreinteCarboneService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getCarbonFootprint(int km, String transport) {
        String url = "https://impactco2.fr/api/v1/transport";
    
        String uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("km", km)
                .queryParam("displayAll", 0)
                .queryParam("transports", transport)
                .queryParam("ignoreRadiativeForcing", 0)
                .queryParam("occupencyRate", 1)
                .queryParam("includeConstruction", 0)
                .queryParam("language", "fr")
                .toUriString();
    
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.set("Accept", "application/json");
    
        HttpEntity<String> entity = new HttpEntity<>(headers);
    
        try {
            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
            return response.getBody();
        } catch (Exception e) {
            return "{\"error\": \"Erreur lors de l'appel à ImpactCO2.\"}";
        }
    }
    
}
