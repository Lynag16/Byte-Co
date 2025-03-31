package com.glop.gestionutilisateurs.controllers;

import com.glop.gestionutilisateurs.dtos.PersonnelDTO;
import com.glop.gestionutilisateurs.entities.Personnel;
import com.glop.gestionutilisateurs.services.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/personnels")
@CrossOrigin(origins = "http://localhost:3000")
public class PersonnelController {

    @Autowired
    private PersonnelService personnelService;

    // GET: récupérer tous les personnels
    @GetMapping
    public ResponseEntity<List<PersonnelDTO>> getAllPersonnels() {
        List<Personnel> personnels = personnelService.getAllPersonnels();
        List<PersonnelDTO> personnelDTOs = personnels.stream()
                .map(personnelService::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(personnelDTOs);
    }

    // GET: récupérer un personnel par ID
    @GetMapping("/{id}")
    public ResponseEntity<PersonnelDTO> getPersonnelById(@PathVariable Long id) {
        Personnel personnel = personnelService.getPersonnelById(id);
        if (personnel != null) {
            PersonnelDTO personnelDTO = personnelService.convertToDTO(personnel);
            return ResponseEntity.ok(personnelDTO);
        }
        return ResponseEntity.notFound().build();
    }

    // POST: créer un nouveau personnel
    @PostMapping
    public ResponseEntity<PersonnelDTO> createPersonnel(@RequestBody PersonnelDTO personnelDTO) {
        Personnel createdPersonnel = personnelService.createPersonnel(personnelDTO);
        PersonnelDTO createdPersonnelDTO = personnelService.convertToDTO(createdPersonnel);
        return new ResponseEntity<>(createdPersonnelDTO, HttpStatus.CREATED); // Utiliser 201 Created
    }

    // PUT: mettre à jour un personnel existant
    @PutMapping("/{id}")
    public ResponseEntity<PersonnelDTO> updatePersonnel(@PathVariable Long id, @RequestBody PersonnelDTO personnelDTO) {
        Personnel updatedPersonnel = personnelService.updatePersonnel(id, personnelDTO);
        if (updatedPersonnel != null) {
            PersonnelDTO updatedPersonnelDTO = personnelService.convertToDTO(updatedPersonnel);
            return ResponseEntity.ok(updatedPersonnelDTO);
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