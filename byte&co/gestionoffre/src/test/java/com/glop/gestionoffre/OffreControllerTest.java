package com.glop.gestionoffre;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(OffreController.class)
public class OffreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OffreService offreService;

    private Offre offre;

    @BeforeEach
    void setUp() {
        offre = new Offre(1L, "Test Offer", 100.0, "Test description", "Test conditions");
    }

    @Test
    void testGetAllOffres() throws Exception {
        when(offreService.getAllOffres()).thenReturn(List.of(offre));

        mockMvc.perform(get("/api/offres"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].nomoffre").value("Test Offer"));
    }

    @Test
    void testGetOffreById() throws Exception {
        when(offreService.getOffreById(1L)).thenReturn(offre);

        mockMvc.perform(get("/api/offres/{id}", 1L))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nomoffre").value("Test Offer"));
    }

    @Test
    void testCreateOffre() throws Exception {
        when(offreService.createOffre(any(Offre.class))).thenReturn(offre);

        mockMvc.perform(post("/api/offres")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nomoffre\":\"Test Offer\",\"prixoffre\":100.0,\"descriptionoffre\":\"Test description\",\"conditionseligibilite\":\"Test conditions\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nomoffre").value("Test Offer"));
    }

    @Test
    void testUpdateOffre() throws Exception {
        when(offreService.updateOffre(eq(1L), any(Offre.class))).thenReturn(offre);

        mockMvc.perform(put("/api/offres/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nomoffre\":\"Updated Offer\",\"prixoffre\":120.0,\"descriptionoffre\":\"Updated description\",\"conditionseligibilite\":\"Updated conditions\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nomoffre").value("Updated Offer"));
    }

    @Test
    void testDeleteOffre() throws Exception {
        doNothing().when(offreService).deleteOffre(1L);

        mockMvc.perform(delete("/api/offres/{id}", 1L))
            .andExpect(status().isNoContent());
    }
}
