package com.example.miniProjeto.controller;

import com.example.miniProjeto.dto.DiscenteDTO;
import com.example.miniProjeto.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discente")
public class DiscenteContoller {

    @Autowired
    private DiscenteService service;

    @GetMapping("/{id}")
    public ResponseEntity<DiscenteDTO> getDiscenteByID(@PathVariable Long id) {
        return ResponseEntity.ok(service.getDiscente(id));
    }


}
