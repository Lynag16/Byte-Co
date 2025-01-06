package glop.gestioncontrats.mappers;

import java.util.stream.Collectors;

import glop.gestioncontrats.dto.OfferDTO;
import glop.gestioncontrats.entities.Offer;

public class OfferMapper {

    public static OfferDTO toDTO(Offer offer) {
        OfferDTO dto = new OfferDTO();
        dto.setId(offer.getId());
        dto.setNomOffer(offer.getNomOffer());
        dto.setPrix(offer.getPrix());
        dto.setDescriptionOffer(offer.getDescriptionOffer());
        dto.setConditionsEligibilite(offer.getConditionsEligibilite());

        // Map contract IDs
        if (offer.getContracts() != null) {
            dto.setContractIds(
                offer.getContracts().stream()
                     .map(contract -> contract.getId())
                     .collect(Collectors.toList())
            );
        }
        return dto;
    }

    public static Offer toEntity(OfferDTO dto) {
        Offer offer = new Offer();
        offer.setId(dto.getId());
        offer.setNomOffer(dto.getNomOffer());
        offer.setPrix(dto.getPrix());
        offer.setDescriptionOffer(dto.getDescriptionOffer());
        offer.setConditionsEligibilite(dto.getConditionsEligibilite());

        // Contracts are not mapped here, as they will be handled separately
        return offer;
    }
}
