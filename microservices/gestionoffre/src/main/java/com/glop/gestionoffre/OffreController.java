package com.glop.gestionoffre;


import com.glop.gestionoffre.Offre;
import com.glop.gestionoffre.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offres")
public class OffreController {

    @Autowired
    private OffreService offreService;

    @GetMapping
    public List<Offre> getAllOffres() {
        return offreService.getAllOffres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offre> getOffreById(@PathVariable Long id) {
        return ResponseEntity.ok(offreService.getOffreById(id));
    }

    @PostMapping
    public ResponseEntity<Offre> createOffre(@RequestBody Offre offre) {
        return ResponseEntity.ok(offreService.createOffre(offre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Offre> updateOffre(@PathVariable Long id, @RequestBody Offre offre) {
        return ResponseEntity.ok(offreService.updateOffre(id, offre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffre(@PathVariable Long id) {
        offreService.deleteOffre(id);
        return ResponseEntity.noContent().build();
    }
}
