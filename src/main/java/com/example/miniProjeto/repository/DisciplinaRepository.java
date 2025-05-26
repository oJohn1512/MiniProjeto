package com.example.miniProjeto.repository;

import com.example.miniProjeto.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    public List<Disciplina> findByCurso(String curso);

}
