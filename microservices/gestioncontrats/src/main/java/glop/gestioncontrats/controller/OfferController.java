package glop.gestioncontrats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import glop.gestioncontrats.dto.OfferDTO;
import glop.gestioncontrats.service.interfaces.OfferService;

@RestController
@RequestMapping("/api/offer")
public class OfferController {

    @Autowired
    private OfferService offerService;

    // Récupérer toutes les offres
    @GetMapping("/all")
    public ResponseEntity<List<OfferDTO>> getAllOffers() {
        List<OfferDTO> offers = offerService.getAllOffers();
        return ResponseEntity.ok(offers);
    }

    // Récupérer une offre par son ID
    @GetMapping("/{id}")
    public ResponseEntity<OfferDTO> getOfferById(@PathVariable int id) {
        OfferDTO offer = offerService.getOfferById(id);
        return ResponseEntity.ok(offer);
    }

    // Ajouter une offre
    @PostMapping("/add")
    public ResponseEntity<OfferDTO> addOffer(@RequestBody OfferDTO offerDTO) {
        OfferDTO savedOffer = offerService.addOffer(offerDTO);
        return ResponseEntity.ok(savedOffer);
    }

    // Modifier une offre
    @PutMapping("/update/{id}")
    public ResponseEntity<OfferDTO> updateOffer(@PathVariable int id, @RequestBody OfferDTO offerDTO) {
        OfferDTO updatedOffer = offerService.updateOffer(id, offerDTO);
        return ResponseEntity.ok(updatedOffer);
    }

    // Supprimer une offre
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable int id) {
        offerService.deleteOffer(id);
        return ResponseEntity.noContent().build();
    }
}
