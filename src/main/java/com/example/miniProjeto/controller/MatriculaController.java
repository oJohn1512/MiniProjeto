package com.example.miniProjeto.controller;

import com.example.miniProjeto.dto.MatriculaDTO;
import com.example.miniProjeto.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matricula")
public class MatriculaController {

    @Autowired
    MatriculaService matriculaService;

    @PostMapping("/{idAluno}/{idDisciplina}")
    public ResponseEntity<MatriculaDTO> matricularDisciplina(@PathVariable long idAluno, @PathVariable long idDisciplina){
        return ResponseEntity.ok(matriculaService.matricularDisciplina(idAluno,idDisciplina));
    }

    @GetMapping("/{idAluno}")
    public ResponseEntity<List<MatriculaDTO>> getDisciplinasMatriculadaAluno(Long idAluno) {
        return ResponseEntity.ok(matriculaService.getDisciplinasMatriculadaAluno(idAluno));
    }

    @DeleteMapping("/{idRegistro}")
    public ResponseEntity<String> cancelarDisciplina(Long idRegistro) {
        return ResponseEntity.ok(matriculaService.cancelarDisciplina(idRegistro));
    }


}
