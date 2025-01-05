package glop.gestioncontrats.mappers;

import glop.gestioncontrats.dto.ContractDTO;
import glop.gestioncontrats.entities.Contract;

public class ContractMapper {

    public static ContractDTO toDTO(Contract contract) {
        ContractDTO dto = new ContractDTO();
        dto.setId(contract.getId());
        dto.setTypeAssurance(contract.getTypeAssurance());
        dto.setDateSouscription(contract.getDateSouscription());
        dto.setDateExpiration(contract.getDateExpiration());
        dto.setStatut(contract.getStatut());
        dto.setMontantContrat(contract.getMontantContrat());
        dto.setEmpreinteCalculee(contract.getEmpreinteCalculee());
        dto.setCompensationCarbone(contract.getCompensationCarbone());

        // Map Offer ID if Offer is not null
        if (contract.getOffer() != null) {
            dto.setOfferId(contract.getOffer().getId());
        }
        return dto;
    }

    public static Contract toEntity(ContractDTO dto) {
        Contract contract = new Contract();
        contract.setId(dto.getId());
        contract.setTypeAssurance(dto.getTypeAssurance());
        contract.setDateSouscription(dto.getDateSouscription());
        contract.setDateExpiration(dto.getDateExpiration());
        contract.setStatut(dto.getStatut());
        contract.setMontantContrat(dto.getMontantContrat());
        contract.setEmpreinteCalculee(dto.getEmpreinteCalculee());
        contract.setCompensationCarbone(dto.getCompensationCarbone());

        // Offer will be set separately in the service
        return contract;
    }
}
