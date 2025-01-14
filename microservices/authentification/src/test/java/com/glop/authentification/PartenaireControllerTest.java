package com.glop.authentification;

import com.glop.authentification.controllers.PartenaireController;
import com.glop.authentification.entities.Partenaire;
import com.glop.authentification.services.PartenaireService;
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
public class PartenaireControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PartenaireService partenaireService;

    @InjectMocks
    private PartenaireController partenaireController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(partenaireController).build();
    }

    @Test
    public void testRegister() throws Exception {
        Partenaire partenaire = new Partenaire();
        partenaire.setIdPartenaire(1);
        partenaire.setEmailPartenaire("test@example.com");
        partenaire.setMotdepassePartenaire("password");

        when(partenaireService.registerpartenaire(any(Partenaire.class))).thenReturn(partenaire);

        mockMvc.perform(post("/api/partenaires/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"emailPartenaire\":\"test@example.com\",\"motdepassePartenaire\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Partenaire inscrit avec succès, ID: 1"));
    }

    @Test
    public void testLogin_Success() throws Exception {
        Map<String, String> loginDetails = new HashMap<>();
        loginDetails.put("emailPartenaire", "test@example.com");
        loginDetails.put("motdepassePartenaire", "password");

        Partenaire partenaire = new Partenaire();
        partenaire.setIdPartenaire(1);
        partenaire.setEmailPartenaire("test@example.com");
        partenaire.setMotdepassePartenaire("password");

        when(partenaireService.authenticatePartenaireAndGetPartenaire("test@example.com", "password")).thenReturn(partenaire);

        mockMvc.perform(post("/api/partenaires/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"emailPartenaire\":\"test@example.com\",\"motdepassePartenaire\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPartenaire").value(1));
    }

    @Test
    public void testLogin_Failure() throws Exception {
        Map<String, String> loginDetails = new HashMap<>();
        loginDetails.put("emailPartenaire", "invalid@example.com");
        loginDetails.put("motdepassePartenaire", "wrongpassword");

        when(partenaireService.authenticatePartenaireAndGetPartenaire("invalid@example.com", "wrongpassword")).thenReturn(null);

        mockMvc.perform(post("/api/partenaires/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"emailPartenaire\":\"invalid@example.com\",\"motdepassePartenaire\":\"wrongpassword\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$").value("Échec de la connexion"));
    }

    @Test
    public void testResetPassword() throws Exception {
        Map<String, String> resetDetails = new HashMap<>();
        resetDetails.put("email", "test@example.com");
        resetDetails.put("newPassword", "newpassword");

        when(partenaireService.resetPassword("test@example.com", "newpassword")).thenReturn(true);

        mockMvc.perform(post("/api/partenaires/reset-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"newPassword\":\"newpassword\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Mot de passe réinitialisé avec succès"));
    }

    @Test
    public void testResetPassword_Failure() throws Exception {
        Map<String, String> resetDetails = new HashMap<>();
        resetDetails.put("email", "test@example.com");
        resetDetails.put("newPassword", "newpassword");

        when(partenaireService.resetPassword("test@example.com", "newpassword")).thenReturn(false);

        mockMvc.perform(post("/api/partenaires/reset-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"newPassword\":\"newpassword\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Échec de la réinitialisation du mot de passe"));
    }
}
