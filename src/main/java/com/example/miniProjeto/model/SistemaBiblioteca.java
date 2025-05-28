package com.example.miniProjeto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class SistemaBiblioteca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idDiscente;
    private Long idLivro;
    private String nomeLivro;
    private LocalDate dataAlocado;
    private LocalDate dataDevolucao;

    public LocalDate getDataAlocado() {
        return dataAlocado;
    }

    public void setDataAlocado(LocalDate dataAlocado) {
        this.dataAlocado = dataAlocado;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDiscente() {
        return idDiscente;
    }

    public void setIdDiscente(Long idDiscente) {
        this.idDiscente = idDiscente;
    }

    public Long getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Long idLivro) {
        this.idLivro = idLivro;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }
}
