package com.glop.gestionsinistres;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sinistres")
public class SinistreController {

    @Autowired
    private SinistreService sinistreService;

    @GetMapping
    public List<Sinistre> getAllSinistres() {
        return sinistreService.getAllSinistres();
    }

    @GetMapping("/{id}")
    public Optional<Sinistre> getSinistreById(@PathVariable Long id) {
        return sinistreService.getSinistreById(id);
    }

    @PostMapping
    public Sinistre createSinistre(@RequestBody Sinistre sinistre) {
        return sinistreService.createSinistre(sinistre);
    }

    @PutMapping("/{id}")
    public Sinistre updateSinistre(@PathVariable Long id, @RequestBody Sinistre sinistreDetails) {
        return sinistreService.updateSinistre(id, sinistreDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteSinistre(@PathVariable Long id) {
        sinistreService.deleteSinistre(id);
    }
}
