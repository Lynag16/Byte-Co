package com.glop.empreintecarbone;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpreinteCarboneController {

    private final EmpreinteCarboneService empreinteCarboneService;

    public EmpreinteCarboneController(EmpreinteCarboneService empreinteCarboneService) {
        this.empreinteCarboneService = empreinteCarboneService;
    }

    @GetMapping("/api/carbon-footprint")
    public String getCarbonFootprint(
            @RequestParam int km,
            @RequestParam String transport) {
        return empreinteCarboneService.getCarbonFootprint(km, transport);
    }
}