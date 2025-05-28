package com.example.miniProjeto.repository;

import com.example.miniProjeto.model.Matricula;
import com.example.miniProjeto.model.SistemaBiblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    Long countByIdAluno(Long idAluno);
    List<Matricula> findByIdDiscente(Long idDiscente);

}
