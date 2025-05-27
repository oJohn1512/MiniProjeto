package com.example.miniProjeto.service;

import com.example.miniProjeto.Exception.ApiException;
import com.example.miniProjeto.dto.DiscenteDTO;
import com.example.miniProjeto.dto.DisciplinaDTO;
import com.example.miniProjeto.dto.MatriculaDTO;
import com.example.miniProjeto.model.Matricula;
import com.example.miniProjeto.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaService {
    @Autowired
    DisciplinasService disciplinasService;

    @Autowired
    DiscenteService discenteService;

    @Autowired
    MatriculaRepository repository;

    public List<DisciplinaDTO> getDisciplinasDisponiveisByAluno(long id) {
        DiscenteDTO aluno = discenteService.getDiscente(id);

        return disciplinasService.getDisciplinasByCurso(aluno.getCurso());
    }

    public MatriculaDTO matricularDisciplina(long idAluno, long idDisciplina) {

        DiscenteDTO aluno = getDiscenteAtivo(idAluno);
        DisciplinaDTO disciplina =getDisciplinaDisponivel(idDisciplina);

        System.out.println("1" + aluno.getCurso());
        System.out.println("2" + disciplina.getCurso());
        System.out.println("3" + aluno.getCurso().equals(disciplina.getCurso()));

        if(aluno.getCurso().trim().equals(disciplina.getCurso().trim())) {
            if(repository.countByIdAluno(idAluno) < 5){
                Matricula matricula = new Matricula();

                matricula.setIdAluno(idAluno);
                matricula.setIdDisciplina(idDisciplina);
                matricula.setCurso(aluno.getCurso());
                matricula.setDisciplina(disciplina.getNome());

                repository.save(matricula);
                System.out.println("Matricula na disciplina feita!");

                return new MatriculaDTO(matricula.getId(), matricula.getIdAluno(), matricula.getIdDisciplina(), matricula.getCurso(),
                        matricula.getDisciplina());
            } else {
                throw new ApiException("Não é possível fazer matricula, aluno já possui limite máximo de 5 disciplinas", HttpStatus.BAD_REQUEST);
            }
        } else {

            System.out.println("1" + aluno.getCurso());
            System.out.println("2" + disciplina.getCurso());
            System.out.println("3" + aluno.getCurso().equals(disciplina.getCurso()));
            throw new ApiException("Disciplina não pertence ao curso do aluno", HttpStatus.BAD_REQUEST);
        }
    }


    private DiscenteDTO getDiscenteAtivo(Long idAluno) {
        DiscenteDTO aluno = discenteService.getDiscente(idAluno);

        if (aluno.getStatus().equals("Ativo")) {
            return aluno;
        } else {
            throw new ApiException("Aluno está com a matricula trancada, não é possível fazer matricula", HttpStatus.BAD_REQUEST);
        }
    }

    private DisciplinaDTO getDisciplinaDisponivel(Long idDisciplina) {
        DisciplinaDTO disciplina = disciplinasService.getDisciplinasById(idDisciplina);

        if (disciplina.getVagas() >= 1) {
            return disciplina;
        } else {
            throw new ApiException("A disciplina solicitada não tem vaga disponível, tente outra", HttpStatus.BAD_REQUEST);
        }
    }


}
