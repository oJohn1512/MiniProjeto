package com.example.miniProjeto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Discente {
    @Id
    private Long id;
    private String nome;
    private String curso;
    private String modalidade;
    private String status;


    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DiscenteModel{" +
                "curso='" + curso + '\'' +
                ", id=" + id +
                ", nome='" + nome + '\'' +
                ", modalidade='" + modalidade + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
