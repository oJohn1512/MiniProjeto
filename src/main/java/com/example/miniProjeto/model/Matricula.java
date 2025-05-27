package com.example.miniProjeto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idAluno;
    private Long idDisciplina;
    private String curso;
    private String disciplina;

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public Long getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Long idDisciplina) {
        this.idDisciplina = idDisciplina;
    }
}
