package com.glop.authentification.services;

import com.glop.authentification.dto.UtilisateurDTO;
import com.glop.authentification.entities.Utilisateur;
import com.glop.authentification.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurDTO.getMotDePasse())); // 🔥 Mot de passe encodé
        utilisateur.setTypeUtilisateur(utilisateurDTO.getTypeUtilisateur());

        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur authenticateUtilisateur(String email, String password) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findByEmail(email);
        if (utilisateurOpt.isPresent()) {
            Utilisateur utilisateur = utilisateurOpt.get();
            if (passwordEncoder.matches(password, utilisateur.getMotDePasse())) {
                return utilisateur;
            }
        }
        return null;
    }
}