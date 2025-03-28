package com.glop.gestionsinistres.controller;

import com.glop.gestionsinistres.dto.RetardTransportSinistreDTO;
import com.glop.gestionsinistres.mapper.RetardTransportSinistreMapper;
import com.glop.gestionsinistres.model.RetardTransportSinistre;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sinistres/retard-transport")
public class RetardTransportSinistreController {

    @PostMapping
    public ResponseEntity<RetardTransportSinistre> declarerRetardTransport(@RequestBody RetardTransportSinistreDTO dto) {
        RetardTransportSinistre created = RetardTransportSinistreMapper.toEntity(dto);
        return ResponseEntity.ok(created);
    }

}