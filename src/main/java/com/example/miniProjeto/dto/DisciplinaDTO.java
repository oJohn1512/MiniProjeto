package com.example.miniProjeto.dto;

public class DisciplinaDTO {
    private Long id;
    private String curso;
    private String nome;
    private int vagas;

    public DisciplinaDTO( Long id, String curso, String nome, int vagas) {
        this.curso = curso;
        this.id = id;
        this.nome = nome;
        this.vagas = vagas;
    }

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }
}
