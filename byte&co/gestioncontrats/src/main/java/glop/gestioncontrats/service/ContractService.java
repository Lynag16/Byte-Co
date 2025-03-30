package glop.gestioncontrats.service;

import glop.gestioncontrats.dto.ContractDTO;
import glop.gestioncontrats.entities.Contract;
import glop.gestioncontrats.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    // === Méthodes CRUD ===

    public List<ContractDTO> getAllContracts() {
        return contractRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ContractDTO getContractById(int id) {
        Optional<Contract> contract = contractRepository.findById(id);
        return contract.map(this::mapToDTO).orElse(null);
    }

    public ContractDTO addContract(ContractDTO contractDTO) {
        Contract contract = mapToEntity(contractDTO);
        Contract saved = contractRepository.save(contract);
        return mapToDTO(saved);
    }

    public ContractDTO updateContract(int id, ContractDTO contractDTO) {
        Contract existing = contractRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setClientId(contractDTO.getClientId());
        existing.setTypeAssurance(contractDTO.getTypeAssurance());
        existing.setDateSouscription(contractDTO.getDateSouscription());
        existing.setDateExpiration(contractDTO.getDateExpiration());
        existing.setStatut(contractDTO.getStatut());
        existing.setMontantContrat(contractDTO.getMontantContrat());
        existing.setEmpreinteCalculee(contractDTO.getEmpreinteCalculee());
        existing.setCompensationCarbone(contractDTO.getCompensationCarbone());

        Contract updated = contractRepository.save(existing);
        return mapToDTO(updated);
    }

    public void deleteContract(int id) {
        contractRepository.deleteById(id);
    }

    // === Méthodes de mapping ===

    private ContractDTO mapToDTO(Contract contract) {
        ContractDTO dto = new ContractDTO();
        dto.setId(contract.getId());
        dto.setClientId(contract.getClientId());
        dto.setTypeAssurance(contract.getTypeAssurance());
        dto.setDateSouscription(contract.getDateSouscription());
        dto.setDateExpiration(contract.getDateExpiration());
        dto.setStatut(contract.getStatut());
        dto.setMontantContrat(contract.getMontantContrat());
        dto.setEmpreinteCalculee(contract.getEmpreinteCalculee());
        dto.setCompensationCarbone(contract.getCompensationCarbone());
        return dto;
    }

    private Contract mapToEntity(ContractDTO dto) {
        Contract contract = new Contract();
        contract.setId(dto.getId());
        contract.setClientId(dto.getClientId());
        contract.setTypeAssurance(dto.getTypeAssurance());
        contract.setDateSouscription(dto.getDateSouscription());
        contract.setDateExpiration(dto.getDateExpiration());
        contract.setStatut(dto.getStatut());
        contract.setMontantContrat(dto.getMontantContrat());
        contract.setEmpreinteCalculee(dto.getEmpreinteCalculee());
        contract.setCompensationCarbone(dto.getCompensationCarbone());
        return contract;
    }
}
