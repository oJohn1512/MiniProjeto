package com.example.miniProjeto.repository;

import com.example.miniProjeto.model.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {
    @Query("SELECT b FROM Biblioteca b WHERE b.status = 'Disponível'")
    public List<Biblioteca> getLivrosByDisponibilidade();
}
