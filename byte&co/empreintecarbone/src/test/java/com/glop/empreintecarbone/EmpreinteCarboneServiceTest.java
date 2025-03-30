package com.glop.empreintecarbone;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class EmpreinteCarboneServiceTest {

    @Test
    public void testGetCarbonFootprint_returnsExpectedResponse() {
        // given
        RestTemplate mockRestTemplate = mock(RestTemplate.class);
        EmpreinteCarboneService service = new EmpreinteCarboneService(mockRestTemplate);

        String mockApiKey = "test-api-key";
        String mockResponse = "{\"result\": \"42 kg CO2\"}";

        // On injecte la clé API manuellement car @Value ne fonctionne pas hors Spring context
        try {
            java.lang.reflect.Field apiKeyField = EmpreinteCarboneService.class.getDeclaredField("API_KEY");
            apiKeyField.setAccessible(true);
            apiKeyField.set(service, mockApiKey);
        } catch (Exception e) {
            fail("Erreur d'injection de la clé API : " + e.getMessage());
        }

        // when
        ResponseEntity<String> responseEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);
        when(mockRestTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenReturn(responseEntity);

        String result = service.getCarbonFootprint(100, "voiture");

        // then
        assertEquals(mockResponse, result);
    }

    @Test
    public void testGetCarbonFootprint_withError_returnsErrorJson() {
        RestTemplate mockRestTemplate = mock(RestTemplate.class);
        EmpreinteCarboneService service = new EmpreinteCarboneService(mockRestTemplate);

        try {
            java.lang.reflect.Field apiKeyField = EmpreinteCarboneService.class.getDeclaredField("API_KEY");
            apiKeyField.setAccessible(true);
            apiKeyField.set(service, "test-api-key");
        } catch (Exception e) {
            fail("Erreur d'injection de la clé API : " + e.getMessage());
        }

        when(mockRestTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenThrow(new RuntimeException("API error"));

        String result = service.getCarbonFootprint(100, "voiture");

        assertEquals("{\"error\": \"Erreur lors de l'appel à ImpactCO2.\"}", result);
    }
}
