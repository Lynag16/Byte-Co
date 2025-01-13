package glop.gestioncontrats;

import java.util.List;

public interface ContractService {

    List<Contract> getAllContracts();

    List<Contract> getAllContractsByUser(int id);
    
    Contract getContractById(int id);

    Contract addContract(Contract contract);

    Contract updateContract(Contract contract);

    void deleteContract(int id);

}
