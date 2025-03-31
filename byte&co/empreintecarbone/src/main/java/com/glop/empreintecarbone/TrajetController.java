package com.glop.empreintecarbone;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/trajet")
@CrossOrigin(origins = "http://localhost:3000")
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

    // Mettre à jour un trajet
    @PutMapping("/{id}")
    public ResponseEntity<Trajet> updateTrajet(@PathVariable Long id, @RequestBody TrajetDTO trajetDTO) {
        Trajet updatedTrajet = trajetService.updateTrajet(id, trajetDTO);
        return ResponseEntity.ok(updatedTrajet);
}


    // Récupérer un trajet par ID de client
    @GetMapping("/trajets/{idClient}")
    public List<Trajet> getTrajetsByClientId(@PathVariable Long idClient) {
        return trajetService.getTrajetByIdClient(idClient);  
    }
    

}
