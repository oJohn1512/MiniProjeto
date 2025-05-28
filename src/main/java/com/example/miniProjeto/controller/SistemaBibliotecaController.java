package com.example.miniProjeto.controller;

import com.example.miniProjeto.dto.SistemaBibliotecaDTO;
import com.example.miniProjeto.service.SistemaBibiliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sistemaBiblioteca")
public class SistemaBibliotecaController {

    @Autowired
    SistemaBibiliotecaService service;

    @GetMapping("/{idAluno}")
    public ResponseEntity<List<SistemaBibliotecaDTO>> getLivrosAlugadoAluno(Long idAluno){
        return ResponseEntity.ok(service.getLivrosAlugadoAluno(idAluno));
    }


    @PostMapping("/{idAluno}/{idLivro}")
    public ResponseEntity<SistemaBibliotecaDTO> alugarLivro(@PathVariable Long idAluno, @PathVariable Long idLivro) {
        return ResponseEntity.ok(service.alugarLivro(idAluno, idLivro));
    }

    @DeleteMapping("/{idRegistro}")
    public ResponseEntity<String> cancelarAluguel(@PathVariable Long idRegistro) {
        return ResponseEntity.ok(service.cancelarAluguel(idRegistro));
    }

}
