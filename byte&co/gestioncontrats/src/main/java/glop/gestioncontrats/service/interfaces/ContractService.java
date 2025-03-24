package glop.gestioncontrats.service.interfaces;

import java.util.List;

import glop.gestioncontrats.dto.ContractDTO;

public interface ContractService {

    List<ContractDTO> getAllContracts();

    ContractDTO getContractById(int id);

    ContractDTO addContract(ContractDTO contractDTO);

    ContractDTO updateContract(int id, ContractDTO contractDTO);

    void deleteContract(int id);
}
