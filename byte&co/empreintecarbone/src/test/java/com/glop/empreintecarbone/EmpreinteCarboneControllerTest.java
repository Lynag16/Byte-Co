package com.glop.empreintecarbone;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmpreinteCarboneController.class)
public class EmpreinteCarboneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpreinteCarboneService empreinteCarboneService;

    @Test
    public void testGetCarbonFootprint_returnsOk() throws Exception {
        when(empreinteCarboneService.getCarbonFootprint(100, "voiture"))
                .thenReturn("{\"result\": \"42 kg CO2\"}");

        mockMvc.perform(get("/api/carbon-footprint")
                        .param("km", "100")
                        .param("transport", "voiture"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"result\": \"42 kg CO2\"}"));
    }
}
