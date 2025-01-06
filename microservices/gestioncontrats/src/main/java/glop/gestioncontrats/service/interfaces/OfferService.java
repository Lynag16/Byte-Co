package glop.gestioncontrats.service.interfaces;

import java.util.List;

import glop.gestioncontrats.dto.OfferDTO;

public interface OfferService {

    List<OfferDTO> getAllOffers();

    OfferDTO getOfferById(int id);

    OfferDTO addOffer(OfferDTO offerDTO);

    OfferDTO updateOffer(int id, OfferDTO offerDTO);

    void deleteOffer(int id);

}
