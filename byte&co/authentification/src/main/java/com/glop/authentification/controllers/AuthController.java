package com.glop.authentification.controllers;

import com.glop.authentification.dto.UtilisateurDTO;
import com.glop.authentification.entities.Utilisateur;
import com.glop.authentification.security.JwtUtil;
import com.glop.authentification.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder; // Add this line

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UtilisateurDTO utilisateurDTO) {
        if (utilisateurDTO.getMotDePasse() == null || utilisateurDTO.getMotDePasse().isEmpty()) {
            return ResponseEntity.status(400).body("Le mot de passe est obligatoire !");
        }

        if (utilisateurDTO.getTypeUtilisateur() == null || utilisateurDTO.getTypeUtilisateur().isEmpty()) {
            utilisateurDTO.setTypeUtilisateur("USER"); // Default to "USER"
        }

        if (utilisateurService.utilisateurExists(utilisateurDTO.getEmail())) {
            return ResponseEntity.status(400).body("Email déjà utilisé !");
        }

        Utilisateur savedUser = utilisateurService.registerUtilisateur(utilisateurDTO);
        return ResponseEntity.ok("Utilisateur enregistré avec succès !");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        Utilisateur utilisateur = utilisateurService.authenticateUtilisateur(email, password);
        if (utilisateur != null) {
            String token = jwtUtil.generateToken(utilisateur.getEmail(), utilisateur.getTypeUtilisateur());
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("email", utilisateur.getEmail());
            response.put("role", utilisateur.getTypeUtilisateur());
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).body("Échec de l'authentification");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String newPassword = request.get("newPassword");

        if (newPassword == null || newPassword.isEmpty()) {
            return ResponseEntity.status(400).body("Le nouveau mot de passe est obligatoire !");
        }

        Utilisateur utilisateur = utilisateurService.findByEmail(email);
        if (utilisateur != null) {
            utilisateur.setMotDePasse(passwordEncoder.encode(newPassword)); // Encode the new password
            utilisateurService.saveUtilisateur(utilisateur);
            return ResponseEntity.ok("Mot de passe réinitialisé avec succès !");
        }

        return ResponseEntity.status(404).body("Utilisateur introuvable !");
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestParam String email) {
        Utilisateur utilisateur = utilisateurService.findByEmail(email);
        if (utilisateur != null) {
            Map<String, Object> profile = new HashMap<>();
            profile.put("email", utilisateur.getEmail());
            profile.put("role", utilisateur.getTypeUtilisateur());
            return ResponseEntity.ok(profile);
        }

        return ResponseEntity.status(404).body("Utilisateur introuvable !");
    }
}