package com.glop.gestionoffre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/offres")
public class OffreController {

    @Autowired
    private OffreService offreService;

    @GetMapping
    public List<OffreDTO> getAllOffres() {
        return offreService.getAllOffres();
    }

    @GetMapping("/{id}")
    public OffreDTO getOffreById(@PathVariable Long id) {
        return offreService.getOffreById(id);
    }

    @PostMapping
    public OffreDTO createOffre(@RequestBody OffreDTO offreDTO) {
        return offreService.createOffre(offreDTO);
    }

    @PutMapping("/{id}")
    public OffreDTO updateOffre(@PathVariable Long id, @RequestBody OffreDTO offreDTO) {
        return offreService.updateOffre(id, offreDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteOffre(@PathVariable Long id) {
        offreService.deleteOffre(id);
    }
}
