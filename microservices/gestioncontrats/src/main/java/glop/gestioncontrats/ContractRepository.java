package glop.gestioncontrats;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Integer> {

    Contract findByClient(Client client);

    Contract findByContractType(String contractType);

    List<Contract> findByClient_ID(int client_id);

    // Contract findByOffer(Offer offer);


}
