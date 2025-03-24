package glop.gestioncontrats.service.impls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import glop.gestioncontrats.dto.ClientDTO;
import glop.gestioncontrats.dto.ContractDTO;
import glop.gestioncontrats.entities.Contract;
import glop.gestioncontrats.mappers.ContractMapper;
import glop.gestioncontrats.repositories.ContractRepository;
import glop.gestioncontrats.service.interfaces.ContractService;
import glop.gestioncontrats.service.interfaces.ServiceClient;

@Service
public class ContractServiceImpl implements ContractService {


    private final ServiceClient serviceClient;

    @Autowired
    public ContractServiceImpl(ServiceClient serviceClient) {
        this.serviceClient = serviceClient;
    }
    
    @Autowired
    private ContractRepository contractRepository;


    @Override
    public List<ContractDTO> getAllContracts() {
        List<Contract> contracts = contractRepository.findAll();
        return contracts.stream()
                        .map(ContractMapper::toDTO)
                        .collect(Collectors.toList());
    }
    @Override
    public ContractDTO getContractById(int id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found with ID: " + id));
        return ContractMapper.toDTO(contract);
    }

    @Override
    public ContractDTO addContract(ContractDTO contractDTO) {
        
        //verifier si le client existe
        ClientDTO client = serviceClient.getClientById(contractDTO.getClient().getIdClient());
        if (client == null) {
            throw new RuntimeException("Client not found with ID: " + contractDTO.getClient().getIdClient());
        }
        
        Contract contract = ContractMapper.toEntity(contractDTO);
        // Validate offer exists
        /*Optional<Offer> offer = offerRepository.findById(contractDTO.getId());
        if (offer.isEmpty()) {
            throw new RuntimeException("Offer not found with ID: " + contractDTO.getId());
        }
        contract.setOffer(offer.get());
        */
        contract = contractRepository.save(contract);
        return ContractMapper.toDTO(contract);
    }

    @Override
    public ContractDTO updateContract(int id, ContractDTO contractDTO) {
        Contract existingContract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found with ID: " + id));

        // Update fields
        existingContract.setTypeAssurance(contractDTO.getTypeAssurance());
        existingContract.setDateSouscription(contractDTO.getDateSouscription());
        existingContract.setDateExpiration(contractDTO.getDateExpiration());
        existingContract.setStatut(contractDTO.getStatut());
        existingContract.setMontantContrat(contractDTO.getMontantContrat());
        existingContract.setEmpreinteCalculee(contractDTO.getEmpreinteCalculee());
        existingContract.setCompensationCarbone(contractDTO.getCompensationCarbone());

        /* 
        // Validate and set offer
        Optional<Offer> offer = offerRepository.findById(contractDTO.getId());
        if (offer.isEmpty()) {
            throw new RuntimeException("Offer not found with ID: " + contractDTO.getId());
        }
        existingContract.setOffer(offer.get());
        */
        existingContract = contractRepository.save(existingContract);
        return ContractMapper.toDTO(existingContract);
    }

    @Override
    public void deleteContract(int id) {
        if (!contractRepository.existsById(id)) {
            throw new RuntimeException("Contract not found with ID: " + id);
        }
        contractRepository.deleteById(id);
    }
}
