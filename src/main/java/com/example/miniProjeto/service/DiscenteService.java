package com.example.miniProjeto.service;

import com.example.miniProjeto.Exception.ApiException;
import com.example.miniProjeto.dto.DiscenteDTO;
import com.example.miniProjeto.model.Biblioteca;
import com.example.miniProjeto.model.Discente;
import com.example.miniProjeto.repository.DiscenteRepository;
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
public class DiscenteService {

    @Autowired
    private DiscenteRepository repository;


    public void getDadosAndSave() {
        final String apiURL = "https://rmi6vdpsq8.execute-api.us-east-2.amazonaws.com/msAluno";
        System.out.println("Iniciando sincronizacao de dados Discentes");

        try {
            repository.deleteAll();
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<List<Discente>> getAPI = restTemplate.exchange(
                    apiURL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Discente>>(){});

            List<Discente> dados = getAPI.getBody();

            if (dados !=null && !dados.isEmpty()) {
                repository.saveAll(dados);
                System.out.println("Dados atualizados dos discentes com sucesso! Quantidade: " + dados.size());
        } else {
                System.out.println("Nenhum dado recebido da API");
            }
        }
        catch (ApiException e) {
            throw new ApiException("Erro ao pegar dados dos Discentes : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public DiscenteDTO getDiscente(Long id) {
        Discente discente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discente não encontrado"));

        return new DiscenteDTO(discente.getId(), discente.getNome(), discente.getCurso(), discente.getModalidade(), discente.getStatus());
    }


}