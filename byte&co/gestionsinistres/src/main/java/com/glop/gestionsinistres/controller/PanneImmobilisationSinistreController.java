package com.glop.gestionsinistres.controller;

import com.glop.gestionsinistres.dto.PanneImmobilisationSinistreDTO;
import com.glop.gestionsinistres.mapper.PanneImmobilisationSinistreMapper;
import com.glop.gestionsinistres.model.PanneImmobilisationSinistre;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sinistres/panne")
public class PanneImmobilisationSinistreController {

    @PostMapping
    public ResponseEntity<PanneImmobilisationSinistre> declarerPanneImmobilisation(@RequestBody PanneImmobilisationSinistreDTO dto) {
        PanneImmobilisationSinistre created = PanneImmobilisationSinistreMapper.toEntity(dto);
        return ResponseEntity.ok(created);
    }

}
