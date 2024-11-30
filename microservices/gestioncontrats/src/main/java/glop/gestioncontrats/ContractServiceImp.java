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
    public Contract getContractById(long id) {
        return contractRepository.findById(id).get();
    }

    @Override
    public void deleteContract(long id) {
        contractRepository.deleteById(id);
    }

    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }
    


}
