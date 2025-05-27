package com.example.miniProjeto.dto;

public class MatriculaDTO {
    private Long id;
    private Long idAluno;
    private Long idDisciplina;
    private String curso;
    private String disciplina;

    public MatriculaDTO(Long id, Long idAluno, Long idDisciplina, String curso, String disciplina) {
        this.id = id;
        this.idAluno = idAluno;
        this.idDisciplina = idDisciplina;
        this.curso = curso;
        this.disciplina = disciplina;
    }

    public MatriculaDTO(Long idAluno, Long idDisciplina, String curso, String disciplina) {
        this.idAluno = idAluno;
        this.idDisciplina = idDisciplina;
        this.curso = curso;
        this.disciplina = disciplina;
    }

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

