package com.glop.authentification.services;

import com.glop.authentification.dto.UtilisateurDTO;
import com.glop.authentification.entities.Utilisateur;
import com.glop.authentification.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean utilisateurExists(String email) {
        return utilisateurRepository.existsByEmail(email);
    }

    public Utilisateur registerUtilisateur(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail(utilisateurDTO.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurDTO.getMotDePasse())); // Encode password
        utilisateur.setTypeUtilisateur(utilisateurDTO.getTypeUtilisateur() != null ? utilisateurDTO.getTypeUtilisateur() : "USER"); // Default to "USER"
        utilisateur.setNom(utilisateurDTO.getNom());
        utilisateur.setPrenom(utilisateurDTO.getPrenom());
        utilisateur.setDateNaissance(utilisateurDTO.getDateNaissance());
        utilisateur.setCodePostal(utilisateurDTO.getCodePostal());
        utilisateur.setIsAdmin(false);
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur authenticateUtilisateur(String email, String password) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findByEmail(email);
        if (utilisateurOpt.isPresent()) {
            Utilisateur utilisateur = utilisateurOpt.get();
            System.out.println("Raw password: " + password);
            System.out.println("Encoded password in DB: " + utilisateur.getMotDePasse());
            if (passwordEncoder.matches(password, utilisateur.getMotDePasse())) { // Compare passwords
                return utilisateur;
            } else {
                System.out.println("Password mismatch!");
            }
        } else {
            System.out.println("User not found with email: " + email);
        }
        return null;
    }

    public void encodeExistingPasswords() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        for (Utilisateur utilisateur : utilisateurs) {
            if (!utilisateur.getMotDePasse().startsWith("$2a$")) { // Check if already encoded
                utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
                utilisateurRepository.save(utilisateur);
            }
        }
    }

    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email).orElse(null);
    }

    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }
}