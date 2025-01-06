package glop.gestioncontrats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import glop.gestioncontrats.entities.Offer;

public interface OfferRepository extends JpaRepository<Offer, Integer> {

}
