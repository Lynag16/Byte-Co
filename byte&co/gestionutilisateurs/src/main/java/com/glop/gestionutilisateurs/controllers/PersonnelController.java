package com.glop.gestionutilisateurs.controllers;

import com.glop.gestionutilisateurs.dtos.PersonnelDTO;
import com.glop.gestionutilisateurs.entities.Personnel;
import com.glop.gestionutilisateurs.services.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personnels")
public class PersonnelController {

    @Autowired
    private PersonnelService personnelService;

    // GET: récupérer tous les personnels
    @GetMapping
    public ResponseEntity<List<Personnel>> getAllPersonnels() {
        List<Personnel> personnels = personnelService.getAllPersonnels();
        return ResponseEntity.ok(personnels);
    }

    // GET: récupérer un personnel par ID
    @GetMapping("/{id}")
    public ResponseEntity<Personnel> getPersonnelById(@PathVariable Long id) {
        Personnel personnel = personnelService.getPersonnelById(id);
        if (personnel != null) {
            return ResponseEntity.ok(personnel);
        }
        return ResponseEntity.notFound().build();
    }

    // POST: créer un nouveau personnel
    @PostMapping
    public ResponseEntity<Personnel> createPersonnel(@RequestBody PersonnelDTO personnelDTO) {
        Personnel createdPersonnel = personnelService.createPersonnel(personnelDTO);
        return ResponseEntity.ok(createdPersonnel);
    }

    // PUT: mettre à jour un personnel existant
    @PutMapping("/{id}")
    public ResponseEntity<Personnel> updatePersonnel(@PathVariable Long id, @RequestBody PersonnelDTO personnelDTO) {
        Personnel updatedPersonnel = personnelService.updatePersonnel(id, personnelDTO);
        if (updatedPersonnel != null) {
            return ResponseEntity.ok(updatedPersonnel);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE: supprimer un personnel
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonnel(@PathVariable Long id) {
        personnelService.deletePersonnel(id);
        return ResponseEntity.noContent().build();
    }
}
