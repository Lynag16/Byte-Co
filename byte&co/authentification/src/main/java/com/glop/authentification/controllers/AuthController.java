package com.glop.authentification.controllers;

import com.glop.authentification.dto.PersonnelDTO;
import com.glop.authentification.dto.UtilisateurDTO;
import com.glop.authentification.entities.Personnel;
import com.glop.authentification.entities.Utilisateur;
import com.glop.authentification.security.JwtUtil;
import com.glop.authentification.services.PersonnelService;
import com.glop.authentification.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private PersonnelService personnelService;

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
    if (utilisateurService.utilisateurExists(utilisateurDTO.getEmail())) {
        errors.put("email", "Email déjà utilisé !");
    }

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

    @PostMapping("/register-gestionnaire")
    public ResponseEntity<?> registerGestionnaire(@RequestBody PersonnelDTO personnelDTO) {
        Map<String, String> errors = new HashMap<>();

        // Validation des champs spécifiques à un gestionnaire
        if (personnelDTO.getNompersonnel() == null || personnelDTO.getNompersonnel().isEmpty()) {
            errors.put("nompersonnel", "Le nom est obligatoire !");
        }
        if (personnelDTO.getPrenompersonnel() == null || personnelDTO.getPrenompersonnel().isEmpty()) {
            errors.put("prenompersonnel", "Le prénom est obligatoire !");
        }
        if (personnelDTO.getEmailpersonnel() == null || personnelDTO.getEmailpersonnel().isEmpty()) {
            errors.put("emailpersonnel", "L'email est obligatoire !");
        }
        if (personnelDTO.setMotdepassepersonnel() == null || personnelDTO.setMotdepassepersonnel().isEmpty()) {
            errors.put("motdepassepersonnel", "Le mot de passe est obligatoire !");
        }

        if (!errors.isEmpty()) {
            return ResponseEntity.status(400).body(errors);
        }

        // Créer un gestionnaire en tant que personnel
        PersonnelDTO savedGestionnaire = personnelService.registerGestionnaire(personnelDTO);

        return ResponseEntity.ok("Gestionnaire enregistré avec succès !");
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

        PersonnelDTO personnelDTO = personnelService.authenticatePersonnelAndGetPersonnel(email, password);
        if (personnelDTO != null) {
            // Génération du token JWT pour l'utilisateur authentifié
            String token = jwtUtil.generateToken(personnelDTO.getEmailpersonnel(), personnelDTO.getRolepersonnel());
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("email", personnelDTO.getEmailpersonnel());
            response.put("role", personnelDTO.getRolepersonnel());
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

    @GetMapping("/profile-personnel")
    @PreAuthorize("hasAnyRole('GESTIONNAIRE', 'MEDECIN', 'MECANICIEN')")
    public ResponseEntity<?> getProfilePersonnel(@RequestParam String email) {
        // Recherche du personnel en fonction de l'email
        PersonnelDTO personnelDTO = personnelService.findByEmailpersonnel(email);

        if (personnelDTO != null) {
            // Si personnel trouvé, retourner les détails
            Map<String, Object> profile = new HashMap<>();
            profile.put("email", personnelDTO.getEmailpersonnel());
            profile.put("role", personnelDTO.getRolepersonnel());
            profile.put("nom", personnelDTO.getNompersonnel());
            profile.put("prenom", personnelDTO.getPrenompersonnel());
            profile.put("telephone", personnelDTO.getTelephonepersonnel());
            profile.put("departement", personnelDTO.getDepartementpersonnel());
            profile.put("adresse", personnelDTO.getAdressepersonnel());
            return ResponseEntity.ok(profile);
        }

        // Si personnel non trouvé
        return ResponseEntity.status(404).body("Personnel introuvable !");
    }

    // TODO
    /*
    @PutMapping("/update-address")
    public ResponseEntity<?> updateAddress(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String newCodePostal = request.get("codePostal");

        if (newCodePostal == null || newCodePostal.isEmpty()) {
            return ResponseEntity.status(400).body("Le code postal est obligatoire !");
        }

        PersonnelDTO personnelDTO = personnelService.findByEmailpersonnel(email);
        if (personnelDTO != null) {
            personnelDTO.setCodePostal(newCodePostal);
            personnelService.save(personnelDTO);

            return ResponseEntity.ok("Adresse mise à jour avec succès !");
        }

        return ResponseEntity.status(404).body("Personnel introuvable !");
    }
     */

}