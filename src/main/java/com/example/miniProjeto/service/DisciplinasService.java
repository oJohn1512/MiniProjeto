package com.example.miniProjeto.service;

import com.example.miniProjeto.Exception.ApiException;
import com.example.miniProjeto.dto.DisciplinaDTO;
import com.example.miniProjeto.model.Discente;
import com.example.miniProjeto.model.Disciplina;
import com.example.miniProjeto.repository.DisciplinaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

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


    public List<DisciplinaDTO> GetDisciplinasByCurso(String curso) {
         List<Disciplina> listaDisciplinas = repository.findByCurso(curso);

         return listaDisciplinas.stream()
                 .map(disciplina -> new DisciplinaDTO(disciplina.getId(),
                         disciplina.getCurso(),
                         disciplina.getNome(),
                         disciplina.getVagas()))
                 .toList();
    }



}
