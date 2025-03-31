package com.glop.empreintecarbone;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/trajet")
public class TrajetController {

    private final TrajetService trajetService;

    public TrajetController(TrajetService trajetService) {
        this.trajetService = trajetService;
    }

    // Créer un trajet
    @PostMapping("/create")
    public ResponseEntity<Trajet> createTrajet(@RequestBody TrajetDTO trajetDTO) {
        Trajet trajet = trajetService.createTrajet(trajetDTO);
        return ResponseEntity.ok(trajet);
    }

    // Récupérer tous les trajets
    @GetMapping("/all")
    public ResponseEntity<List<Trajet>> getAllTrajets() {
        return ResponseEntity.ok(trajetService.getAllTrajets());
    }

    // Récupérer un trajet par ID
    @GetMapping("/{id}")
    public ResponseEntity<Trajet> getTrajetById(@PathVariable Long id) {
        Trajet trajet = trajetService.getTrajetById(id);
        return ResponseEntity.ok(trajet);
    }

    // Supprimer un trajet
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrajet(@PathVariable Long id) {
        trajetService.deleteTrajet(id);
        return ResponseEntity.noContent().build();
    }
}
