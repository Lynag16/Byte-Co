package glop.gestioncontrats;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImp implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Override
    public Contract addContract(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public Contract updateContract(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public Contract getContractById(int id) {
        return contractRepository.findById(id).get();
    }

    @Override
    public void deleteContract(int id) {
        contractRepository.deleteById(id);
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public List<Contract> getAllContractsByUser(int client_id) {
        return contractRepository.findByClient_ID(client_id);
    }
    


}
