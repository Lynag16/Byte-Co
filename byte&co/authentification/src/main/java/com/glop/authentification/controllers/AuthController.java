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
    Map<String, String> errors = new HashMap<>();

    if (utilisateurDTO.getMotDePasse() == null || utilisateurDTO.getMotDePasse().isEmpty()) {
        errors.put("motDePasse", "Le mot de passe est obligatoire !");
    }
    if (utilisateurDTO.getEmail() == null || utilisateurDTO.getEmail().isEmpty()) {
        errors.put("email", "L'email est obligatoire !");
    }
    if (utilisateurDTO.getNom() == null || utilisateurDTO.getNom().isEmpty()) {
        errors.put("nom", "Le nom est obligatoire !");
    }
    if (utilisateurDTO.getPrenom() == null || utilisateurDTO.getPrenom().isEmpty()) {
        errors.put("prenom", "Le prénom est obligatoire !");
    }
    if (utilisateurDTO.getDateNaissance() == null || utilisateurDTO.getDateNaissance().isEmpty()) {
        errors.put("dateNaissance", "La date de naissance est obligatoire !");
    }
    if (utilisateurDTO.getCodePostal() == null || utilisateurDTO.getCodePostal().isEmpty()) {
        errors.put("codePostal", "Le code postal est obligatoire !");
    }

    // Vérifier si l'email existe déjà
    if (utilisateurService.utilisateurExists(utilisateurDTO.getEmail())) {
        errors.put("email", "Email déjà utilisé !");
    }

    // Retourner les erreurs si elles existent
    if (!errors.isEmpty()) {
        return ResponseEntity.status(400).body(errors);
    }

    // Définir un rôle par défaut si non fourni
    if (utilisateurDTO.getTypeUtilisateur() == null || utilisateurDTO.getTypeUtilisateur().isEmpty()) {
        utilisateurDTO.setTypeUtilisateur("USER");
    }

    // Créer un nouvel utilisateur
    Utilisateur utilisateur = new Utilisateur();
    utilisateur.setEmail(utilisateurDTO.getEmail());
    utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurDTO.getMotDePasse()));
    utilisateur.setTypeUtilisateur(utilisateurDTO.getTypeUtilisateur());
    utilisateur.setNom(utilisateurDTO.getNom());
    utilisateur.setPrenom(utilisateurDTO.getPrenom());
    utilisateur.setDateNaissance(utilisateurDTO.getDateNaissance());
    utilisateur.setCodePostal(utilisateurDTO.getCodePostal());

    // Enregistrer l'utilisateur
    utilisateurService.saveUtilisateur(utilisateur);

    return ResponseEntity.ok("Utilisateur enregistré avec succès !");
    }

    // Enregistrement d’un client (CLIENT)
    @PostMapping("/register-client")
    public ResponseEntity<?> registerClient(@RequestBody UtilisateurDTO utilisateurDTO) {
        if (utilisateurDTO.getMotDePasse() == null || utilisateurDTO.getMotDePasse().isEmpty()) {
            return ResponseEntity.status(400).body("Le mot de passe est obligatoire !");
        }

        if (utilisateurService.utilisateurExists(utilisateurDTO.getEmail())) {
            return ResponseEntity.status(400).body("Email déjà utilisé !");
        }

        utilisateurDTO.setTypeUtilisateur("CLIENT");
        utilisateurService.registerUtilisateur(utilisateurDTO);
        return ResponseEntity.ok("Client enregistré avec succès !");
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
        String currentPassword = request.get("currentPassword");
        String newPassword = request.get("newPassword");

        if (newPassword == null || newPassword.isEmpty()) {
            return ResponseEntity.status(400).body("Le nouveau mot mot de passe est obligatoire !");
        }

        Utilisateur utilisateur = utilisateurService.findByEmail(email);
        if (utilisateur != null) {
            if (!passwordEncoder.matches(currentPassword, utilisateur.getMotDePasse())) {
                return ResponseEntity.status(400).body("Le mot de passe actuel est incorrect !");
            }

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
            profile.put("nom", utilisateur.getNom());
            profile.put("prenom", utilisateur.getPrenom());
            profile.put("dateNaissance", utilisateur.getDateNaissance());
            profile.put("codePostal", utilisateur.getCodePostal());
            return ResponseEntity.ok(profile);
        }

        return ResponseEntity.status(404).body("Utilisateur introuvable !");
    }

    @PutMapping("/update-address")
    public ResponseEntity<?> updateAddress(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String newCodePostal = request.get("codePostal");

        if (newCodePostal == null || newCodePostal.isEmpty()) {
            return ResponseEntity.status(400).body("Le code postal est obligatoire !");
        }

        Utilisateur utilisateur = utilisateurService.findByEmail(email);
        if (utilisateur != null) {
            utilisateur.setCodePostal(newCodePostal);
            utilisateurService.saveUtilisateur(utilisateur);
            return ResponseEntity.ok("Adresse mise à jour avec succès !");
        }

        return ResponseEntity.status(404).body("Utilisateur introuvable !");
    }

    
}