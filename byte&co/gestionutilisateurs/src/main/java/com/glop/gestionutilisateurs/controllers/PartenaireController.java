package com.glop.gestionutilisateurs.controllers;

import com.glop.gestionutilisateurs.dtos.PartenaireDTO;
import com.glop.gestionutilisateurs.services.PartenaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/api/partenaires", "/api/partenaires/"})
@CrossOrigin(origins = "http://localhost:3000")
public class PartenaireController {

    @Autowired
    private PartenaireService partenaireService;

    @GetMapping
    public List<PartenaireDTO> getAllPartenaires() {
        return partenaireService.getAllPartenaires();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartenaireDTO> getPartenaireById(@PathVariable int id) {
        return partenaireService.getPartenaireById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PartenaireDTO> createPartenaire(@RequestBody PartenaireDTO dto) {
        return ResponseEntity.ok(partenaireService.createPartenaire(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartenaireDTO> updatePartenaire(@PathVariable int id, @RequestBody PartenaireDTO dto) {
        return partenaireService.updatePartenaire(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartenaire(@PathVariable int id) {
        if (partenaireService.deletePartenaire(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}