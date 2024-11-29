package glop.gestioncontrats;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    Contract findByClient(Client client);

    Contract findByContractType(String contractType);

    // Contract findByOffer(Offer offer);


}
