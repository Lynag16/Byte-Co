// filepath: /home/m2ipint/lynda.agouazi.etu/M2_MIAGE/GLOP/Byte-Co/byte&co/gestioncontrats/src/main/java/glop/gestioncontrats/service/CarbonFootprintClient.java
package glop.gestioncontrats.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CarbonFootprintClient {

    private final RestTemplate restTemplate;

    @Value("${empreintecarbone.url}")
    private String empreinteCarboneUrl;

    public CarbonFootprintClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getCarbonFootprint(int km, String transport) {
        String url = empreinteCarboneUrl + "/api/carbon-footprint?km=" + km + "&transport=" + transport;
        return restTemplate.getForObject(url, String.class);
    }
}