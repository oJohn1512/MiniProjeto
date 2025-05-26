package com.example.miniProjeto.service;

import com.example.miniProjeto.Exception.ApiException;
import com.example.miniProjeto.dto.BibliotecaDTO;
import com.example.miniProjeto.model.Biblioteca;
import com.example.miniProjeto.repository.BibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BibliotecaService {
    private final String apiURL = "https://qiiw8bgxka.execute-api.us-east-2.amazonaws.com/acervo/biblioteca";
    private final WebClient webClient;

    @Autowired
    private BibliotecaRepository repository;

    public BibliotecaService() {
        this.webClient = WebClient.builder().baseUrl(apiURL).build();
    }

    public void getDadosAndSave() {
        System.out.println("Iniciando sincronizacao de dados Biblioteca");

        try {
            repository.deleteAll();

            List<Biblioteca> dados =  webClient
                    .get()
                    .uri("")
                    .retrieve()
                    .bodyToFlux(Biblioteca.class)
                    .collectList()
                    .block();

            if (dados !=null && !dados.isEmpty()) {
                repository.saveAll(dados);
                System.out.println("Dados atualizados da biblioteca com sucesso! Quantidade: " + dados.size());
            } else {
                System.out.println("Nenhum dado recebido da API");
            }
        }
        catch (ApiException e) {
            throw new ApiException("Erro ao pegar dados da Biblioteca: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<BibliotecaDTO> getLivrosByDisponibilidade(){
        List<Biblioteca> livros = repository.getLivrosByDisponibilidade();

        return livros.stream()
                .map(biblioteca -> new BibliotecaDTO(
                        biblioteca.getId(),
                        biblioteca.getTitulo(),
                        biblioteca.getAutor(),
                        biblioteca.getAno(),
                        biblioteca.getStatus()))
                .toList();
    }
}
