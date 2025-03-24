package glop.gestioncontrats.service.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import glop.gestioncontrats.dto.ClientDTO;

@FeignClient(name = "authentification", url = "http://localhost:8081")
public interface ServiceClient {

    @GetMapping("/api/clients/{id}")
    ClientDTO getClientById(@PathVariable int id);
}
