package com.example.miniProjeto.controller;

import com.example.miniProjeto.dto.DisciplinaDTO;
import com.example.miniProjeto.model.Disciplina;
import com.example.miniProjeto.service.DisciplinasService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {

    @Autowired
    DisciplinasService disciplinasService;

    @GetMapping("/{curso}")
    public ResponseEntity<List<DisciplinaDTO>> getDisciplinasByCurso(@PathVariable String curso) {
        return ResponseEntity.ok(disciplinasService.getDisciplinasByCurso(curso));
    }
}
