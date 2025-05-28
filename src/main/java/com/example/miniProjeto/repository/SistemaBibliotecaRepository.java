package com.example.miniProjeto.repository;

import com.example.miniProjeto.model.Biblioteca;
import com.example.miniProjeto.model.SistemaBiblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SistemaBibliotecaRepository extends JpaRepository<SistemaBiblioteca, Long> {
    List<SistemaBiblioteca> findByIdDiscente(Long idDiscente);
}