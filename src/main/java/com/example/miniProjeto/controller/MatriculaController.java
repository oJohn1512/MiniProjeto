package com.example.miniProjeto.controller;

import com.example.miniProjeto.dto.MatriculaDTO;
import com.example.miniProjeto.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matricula")
public class MatriculaController {

    @Autowired
    MatriculaService matriculaService;

    @PostMapping("/{idAluno}/{idDisciplina}")
    public ResponseEntity<MatriculaDTO> matricularDisciplina(@PathVariable  long idAluno, @PathVariable long idDisciplina){
        return ResponseEntity.ok(matriculaService.matricularDisciplina(idAluno,idDisciplina));
    }

}
