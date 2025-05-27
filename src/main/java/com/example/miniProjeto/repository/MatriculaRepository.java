package com.example.miniProjeto.repository;

import com.example.miniProjeto.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    Long countByIdAluno(Long idAluno);
}
