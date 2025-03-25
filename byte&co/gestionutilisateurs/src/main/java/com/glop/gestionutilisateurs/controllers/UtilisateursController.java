package com.glop.gestionutilisateurs.controllers;

import com.glop.gestionutilisateurs.entities.Client;

import com.glop.gestionutilisateurs.entities.Utilisateurs;
import com.glop.gestionutilisateurs.services.UtilisateursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateursController {
    @Autowired
    private UtilisateursService utilisateursService;

    @PostMapping
    public Utilisateurs creerUtilisateurs(@RequestBody Utilisateurs utilisateur) {
        return utilisateursService.creerUtilisateurs(utilisateur);
    }

    @GetMapping
    public List<Utilisateurs> getAllUtilisateurss() {
        return utilisateursService.getAllUtilisateurss();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateurs> getUtilisateursById(@PathVariable Long id) {
        return utilisateursService.getUtilisateursById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateurs(@PathVariable Long id) {
        utilisateursService.deleteUtilisateurs(id);
        return ResponseEntity.noContent().build();
    }
}