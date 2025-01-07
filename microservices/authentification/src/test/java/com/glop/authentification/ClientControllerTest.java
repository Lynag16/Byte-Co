package com.glop.authentification.controllers;

import com.glop.authentification.dto.ClientDTO;
import com.glop.authentification.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    public void testRegister() throws Exception {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setIdClient(1);
        clientDTO.setEmail("client@example.com");
        clientDTO.setMotdepasse("password");

        when(clientService.registerClient(any(ClientDTO.class))).thenReturn(clientDTO);

        mockMvc.perform(post("/api/clients/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"client@example.com\",\"motdepasse\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Client inscrit avec succès, ID: 1"));
    }

    @Test
    public void testLogin_Success() throws Exception {
        Map<String, String> loginDetails = new HashMap<>();
        loginDetails.put("email", "client@example.com");
        loginDetails.put("motdepasse", "password");

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setIdClient(1);
        clientDTO.setEmail("client@example.com");
        clientDTO.setMotdepasse("password");

        when(clientService.authenticateClientAndGetClient("client@example.com", "password")).thenReturn(clientDTO);

        mockMvc.perform(post("/api/clients/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"client@example.com\",\"motdepasse\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idClient").value(1));
    }

    @Test
    public void testLogin_Failure() throws Exception {
        Map<String, String> loginDetails = new HashMap<>();
        loginDetails.put("email", "invalid@example.com");
        loginDetails.put("motdepasse", "wrongpassword");

        when(clientService.authenticateClientAndGetClient("invalid@example.com", "wrongpassword")).thenReturn(null);

        mockMvc.perform(post("/api/clients/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"invalid@example.com\",\"motdepasse\":\"wrongpassword\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$").value("Échec de la connexion"));
    }

    @Test
    public void testResetPassword() throws Exception {
        Map<String, String> resetDetails = new HashMap<>();
        resetDetails.put("email", "client@example.com");
        resetDetails.put("newPassword", "newpassword");

        when(clientService.resetPassword("client@example.com", "newpassword")).thenReturn(true);

        mockMvc.perform(post("/api/clients/reset-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"client@example.com\",\"newPassword\":\"newpassword\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Mot de passe réinitialisé avec succès"));
    }

    @Test
    public void testResetPassword_Failure() throws Exception {
        Map<String, String> resetDetails = new HashMap<>();
        resetDetails.put("email", "client@example.com");
        resetDetails.put("newPassword", "newpassword");

        when(clientService.resetPassword("client@example.com", "newpassword")).thenReturn(false);

        mockMvc.perform(post("/api/clients/reset-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"client@example.com\",\"newPassword\":\"newpassword\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Échec de la réinitialisation du mot de passe"));
    }
}
