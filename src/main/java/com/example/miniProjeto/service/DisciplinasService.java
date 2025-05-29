package com.example.miniProjeto.service;

import com.example.miniProjeto.exception.ApiException;
import com.example.miniProjeto.dto.DisciplinaDTO;
import com.example.miniProjeto.model.Disciplina;
import com.example.miniProjeto.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DisciplinasService {

    @Autowired
    private DisciplinaRepository repository;


    public void getDadosAndSave() {
        final String apiURL = "https://sswfuybfs8.execute-api.us-east-2.amazonaws.com/disciplinaServico/msDisciplina";
        System.out.println("Iniciando sincronizacao de dados Disciplinas");

        try {
            repository.deleteAll();
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<List<Disciplina>> getAPI = restTemplate.exchange(
                    apiURL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Disciplina>>(){});

            List<Disciplina> dados = getAPI.getBody();

            if (dados !=null && !dados.isEmpty()) {
                repository.saveAll(dados);
                System.out.println("Dados atualizados das disciplinas com sucesso! Quantidade: " + dados.size());
            } else {
                System.out.println("Nenhum dado recebido da API");
            }
        }
        catch (ApiException e) {
            throw new ApiException("Erro ao pegar dados dos Disciplinas : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public List<DisciplinaDTO> getDisciplinasByCurso(String curso) {
         List<Disciplina> listaDisciplinas = repository.findByCurso(curso);

         if (!listaDisciplinas.isEmpty()) {
             System.out.println("cai na lista");
             return listaDisciplinas.stream()
                     .map(disciplina -> new DisciplinaDTO(disciplina.getId(),
                             disciplina.getCurso(),
                             disciplina.getNome(),
                             disciplina.getVagas()))
                     .toList();
         } else {
             throw new ApiException("Nenhuma disciplina disponivel", HttpStatus.BAD_REQUEST);
         }

    }

    public DisciplinaDTO getDisciplinasById(Long id) {
        Disciplina disciplina = repository.findById(id)
                .orElseThrow(() -> new ApiException("Disciplina não encontrado", HttpStatus.BAD_REQUEST));

        return new DisciplinaDTO(disciplina.getId(), disciplina.getCurso(), disciplina.getNome(), disciplina.getVagas());
    }


    public void diminuirVaga(Long id) {
        Disciplina disciplina = repository.findById(id)
                .orElseThrow(() -> new ApiException("Disciplina não encontrado", HttpStatus.BAD_REQUEST));

        disciplina.setVagas(disciplina.getVagas() -1 );

        repository.save(disciplina);
    }


    public void aumentarVaga(Long id) {
        Disciplina disciplina = repository.findById(id)
                .orElseThrow(() -> new ApiException("Disciplina não encontrado", HttpStatus.BAD_REQUEST));

        disciplina.setVagas(disciplina.getVagas() + 1 );

        repository.save(disciplina);
    }




}
