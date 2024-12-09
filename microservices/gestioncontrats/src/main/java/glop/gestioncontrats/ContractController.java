package glop.gestioncontrats;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.websocket.server.PathParam;

@RestController
@Controller
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;

    //recuperer tous les contrats
    @GetMapping("/getAll")
    public List<Contract> getAllContracts() {
        return contractService.getAllContracts();
    }

    @GetMapping("/getAllByUser/{client_id}")
    public ResponseEntity<List<Contract>> getAllContractsByUser(@PathParam("client_id") int id) {
        List <Contract> contracts = contractService.getAllContractsByUser(id);
        return ResponseEntity.ok(contracts);
    }

    //recuperer un contrat par son id
    @GetMapping("/get/{id}")
    public ResponseEntity<Contract> getContractById(@PathParam("id") int id) {
        return ResponseEntity.ok(contractService.getContractById(id));
    }

    //ajouter un contrat
    @PostMapping("/add")
    public ResponseEntity<Contract> addContract(@RequestBody Contract contract) {
        return ResponseEntity.ok(contractService.addContract(contract));
    }

    //modifier un contrat
    @PutMapping("/update/")
    public ResponseEntity<Contract> updateContract(@RequestBody Contract contract) {
        return ResponseEntity.ok(contractService.updateContract(contract));
    }

    //supprimer un contrat
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteContract(@PathParam("id") int id) {
        contractService.deleteContract(id);
        return ResponseEntity.noContent().build();
    }

}
