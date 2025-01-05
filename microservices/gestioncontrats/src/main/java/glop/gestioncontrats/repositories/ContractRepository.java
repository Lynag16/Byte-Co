package glop.gestioncontrats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import glop.gestioncontrats.entities.Contract;

public interface ContractRepository extends JpaRepository<Contract, Integer> {

}
