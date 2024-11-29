package glop.gestioncontrats;

import java.util.List;

public interface ContractService {

    List<Contract> getAllContracts();
    
    Contract getContractById(long id);

    Contract addContract(Contract contract);

    Contract updateContract(Contract contract);

    void deleteContract(long id);

}
