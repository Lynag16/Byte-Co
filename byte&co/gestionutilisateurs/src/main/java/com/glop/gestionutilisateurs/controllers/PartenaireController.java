package com.glop.gestionutilisateurs.controllers;

import com.glop.gestionutilisateurs.dtos.PartenaireDTO;
import com.glop.gestionutilisateurs.services.PartenaireService;
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
    public List<PartenaireDTO> getAllPartenaires() {
        return partenaireService.getAllPartenaires();
    }

    @GetMapping("/{id}")
    public PartenaireDTO getPartenaireById(@PathVariable int id) {
        return partenaireService.getPartenaireById(id);
    }

    @PostMapping("/register")
    public PartenaireDTO registerPartenaire(@RequestBody PartenaireDTO partenaireDTO) {
        return partenaireService.createPartenaire(partenaireDTO);
    }

    @PutMapping("/{id}")
    public PartenaireDTO updatePartenaire(@PathVariable int id, @RequestBody PartenaireDTO partenaireDTO) {
        return partenaireService.updatePartenaire(id, partenaireDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePartenaire(@PathVariable int id) {
        partenaireService.deletePartenaire(id);
    }
}
