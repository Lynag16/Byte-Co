package glop.gestioncontrats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import glop.gestioncontrats.dto.ContractDTO;
import glop.gestioncontrats.service.interfaces.ContractService;

@RestController
@RequestMapping("/api/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;

    // Récupérer tous les contrats
    @GetMapping("/all")
    public ResponseEntity<List<ContractDTO>> getAllContracts() {
        List<ContractDTO> contracts = contractService.getAllContracts();
        return ResponseEntity.ok(contracts);
    }

    // Récupérer un contrat par son ID
    @GetMapping("/{id}")
    public ResponseEntity<ContractDTO> getContractById(@PathVariable int id) {
        ContractDTO contract = contractService.getContractById(id);
        return ResponseEntity.ok(contract);
    }

    // Ajouter un contrat
    @PostMapping("/add")
    public ResponseEntity<ContractDTO> addContract(@RequestBody ContractDTO contractDTO) {
        ContractDTO savedContract = contractService.addContract(contractDTO);
        return ResponseEntity.ok(savedContract);
    }

    // Mettre à jour un contrat
    @PutMapping("/update/{id}")
    public ResponseEntity<ContractDTO> updateContract(@PathVariable int id, @RequestBody ContractDTO contractDTO) {
        ContractDTO updatedContract = contractService.updateContract(id, contractDTO);
        return ResponseEntity.ok(updatedContract);
    }

    // Supprimer un contrat
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable int id) {
        contractService.deleteContract(id);
        return ResponseEntity.noContent().build();
    }
}
