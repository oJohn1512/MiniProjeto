package com.example.miniProjeto.controller;

import com.example.miniProjeto.dto.BibliotecaDTO;
import com.example.miniProjeto.service.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/biblioteca")
public class BibliotecaController {

    @Autowired
    BibliotecaService bibliotecaService;

    @GetMapping
    public ResponseEntity<List<BibliotecaDTO>> getLivrosByDisponibilidade(){
        return ResponseEntity.ok(bibliotecaService.getLivrosByDisponibilidade());
    }
}
