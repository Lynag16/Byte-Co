package com.glop.gestionutilisateurs.controllers;

import com.glop.gestionutilisateurs.dtos.PartenaireDTO;
import com.glop.gestionutilisateurs.services.PartenaireService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partenaires")
public class PartenaireController {

    private final PartenaireService partenaireService;

    public PartenaireController(PartenaireService partenaireService) {
        this.partenaireService = partenaireService;
    }

    @GetMapping("/")
    public ResponseEntity<List<PartenaireDTO>> getAllPartenaires() {
        List<PartenaireDTO> partenaires = partenaireService.getAllPartenaires();
        return new ResponseEntity<>(partenaires, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartenaireDTO> getPartenaireById(@PathVariable int id) {
        PartenaireDTO partenaire = partenaireService.getPartenaireById(id);
        if (partenaire != null) {
            return new ResponseEntity<>(partenaire, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<PartenaireDTO> registerPartenaire(@RequestBody PartenaireDTO partenaireDTO) {
        PartenaireDTO createdPartenaire = partenaireService.createPartenaire(partenaireDTO);
        return new ResponseEntity<>(createdPartenaire, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartenaireDTO> updatePartenaire(@PathVariable int id, @RequestBody PartenaireDTO partenaireDTO) {
        PartenaireDTO updatedPartenaire = partenaireService.updatePartenaire(id, partenaireDTO);
        if (updatedPartenaire != null) {
            return new ResponseEntity<>(updatedPartenaire, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartenaire(@PathVariable int id) {
        partenaireService.deletePartenaire(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}