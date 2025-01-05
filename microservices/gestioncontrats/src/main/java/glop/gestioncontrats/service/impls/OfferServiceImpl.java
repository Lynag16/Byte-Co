package glop.gestioncontrats.service.impls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glop.gestioncontrats.dto.OfferDTO;
import glop.gestioncontrats.entities.Offer;
import glop.gestioncontrats.mappers.OfferMapper;
import glop.gestioncontrats.repositories.OfferRepository;
import glop.gestioncontrats.service.interfaces.OfferService;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Override
    public List<OfferDTO> getAllOffers() {
        List<Offer> offers = offerRepository.findAll();
        return offers.stream()
                     .map(OfferMapper::toDTO)
                     .collect(Collectors.toList());
    }

    @Override
    public OfferDTO getOfferById(int id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found with ID: " + id));
        return OfferMapper.toDTO(offer);
    }

    @Override
    public OfferDTO addOffer(OfferDTO offerDTO) {
        Offer offer = OfferMapper.toEntity(offerDTO);
        offer = offerRepository.save(offer);
        return OfferMapper.toDTO(offer);
    }

    @Override
    public OfferDTO updateOffer(int id, OfferDTO offerDTO) {
        Offer existingOffer = offerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found with ID: " + id));

        // Update fields
        existingOffer.setNomOffer(offerDTO.getNomOffer());
        existingOffer.setPrix(offerDTO.getPrix());
        existingOffer.setDescriptionOffer(offerDTO.getDescriptionOffer());
        existingOffer.setConditionsEligibilite(offerDTO.getConditionsEligibilite());

        existingOffer = offerRepository.save(existingOffer);
        return OfferMapper.toDTO(existingOffer);
    }

    @Override
    public void deleteOffer(int id) {
        if (!offerRepository.existsById(id)) {
            throw new RuntimeException("Offer not found with ID: " + id);
        }
        offerRepository.deleteById(id);
    }
}
