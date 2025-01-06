package com.glop.authentification;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glop.authentification.controllers.PersonnelController;
import com.glop.authentification.entities.Personnel;
import com.glop.authentification.services.PersonnelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

public class PersonnelControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PersonnelService personnelService;

    @InjectMocks
    private PersonnelController personnelController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(personnelController).build();
    }

    @Test
    void testRegister_Success() throws Exception {
        Personnel personnel = new Personnel();
        personnel.setIdpersonnel(1L);
        when(personnelService.registerPersonnel(any(Personnel.class))).thenReturn(personnel);

        mockMvc.perform(post("/api/personnels/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(personnel)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Personnel inscrit avec succès, ID: 1"));
    }

    @Test
    void testRegister_Failure() throws Exception {
        when(personnelService.registerPersonnel(any(Personnel.class)))
                .thenThrow(new RuntimeException("Erreur de validation"));

        mockMvc.perform(post("/api/personnels/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Personnel())))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Erreur lors de l'inscription : Erreur de validation"));
    }

    @Test
    void testLogin_Success() throws Exception {
        Personnel personnel = new Personnel();
        personnel.setIdpersonnel(1L);
        personnel.setEmailpersonnel("test@example.com");
        personnel.setmotdepassepersonnel("password");

        when(personnelService.authenticatePersonnelAndGetPersonnel(eq("test@example.com"), eq("password")))
                .thenReturn(personnel);

        Map<String, String> loginDetails = new HashMap<>();
        loginDetails.put("emailpersonnel", "test@example.com");
        loginDetails.put("motdepassepersonnel", "password");

        mockMvc.perform(post("/api/personnels/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDetails)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idpersonnel").value(1));
    }

    @Test
    void testLogin_Failure_InvalidCredentials() throws Exception {
        when(personnelService.authenticatePersonnelAndGetPersonnel(eq("wrong@example.com"), eq("wrongpassword")))
                .thenReturn(null);

        Map<String, String> loginDetails = new HashMap<>();
        loginDetails.put("emailpersonnel", "wrong@example.com");
        loginDetails.put("motdepassepersonnel", "wrongpassword");

        mockMvc.perform(post("/api/personnels/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDetails)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Échec de la connexion : identifiants invalides"));
    }

    @Test
    void testResetPassword_Success() throws Exception {
        when(personnelService.resetPassword(eq("test@example.com"), eq("newpassword"))).thenReturn(true);

        Map<String, String> resetDetails = new HashMap<>();
        resetDetails.put("email", "test@example.com");
        resetDetails.put("newPassword", "newpassword");

        mockMvc.perform(post("/api/personnels/reset-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resetDetails)))
                .andExpect(status().isOk())
                .andExpect(content().string("Mot de passe réinitialisé avec succès"));
    }

    @Test
    void testResetPassword_Failure() throws Exception {
        when(personnelService.resetPassword(eq("invalid@example.com"), eq("newpassword"))).thenReturn(false);

        Map<String, String> resetDetails = new HashMap<>();
        resetDetails.put("email", "invalid@example.com");
        resetDetails.put("newPassword", "newpassword");

        mockMvc.perform(post("/api/personnels/reset-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resetDetails)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Échec de la réinitialisation du mot de passe"));
    }
}
