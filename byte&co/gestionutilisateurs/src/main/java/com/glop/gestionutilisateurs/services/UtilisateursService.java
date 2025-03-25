package com.glop.gestionutilisateurs.services;

import com.glop.gestionutilisateurs.entities.Client;

import com.glop.gestionutilisateurs.entities.Utilisateurs;
import com.glop.gestionutilisateurs.repositories.UtilisateursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateursService {
    @Autowired
    private UtilisateursRepository utilisateursRepository;

    public Utilisateurs creerUtilisateurs(Utilisateurs utilisateur) {
        return utilisateursRepository.save(utilisateur);
    }

    public List<Utilisateurs> getAllUtilisateurss() {
        return utilisateursRepository.findAll();
    }

    public Optional<Utilisateurs> getUtilisateursById(Long id) {
        return utilisateursRepository.findById(id);
    }

    public void deleteUtilisateurs(Long id) {
        utilisateursRepository.deleteById(id);
    }
}