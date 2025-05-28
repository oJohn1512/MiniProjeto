package com.example.miniProjeto.service;

import com.example.miniProjeto.exception.ApiException;
import com.example.miniProjeto.dto.BibliotecaDTO;
import com.example.miniProjeto.model.Biblioteca;
import com.example.miniProjeto.repository.BibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BibliotecaService {

    @Autowired
    private BibliotecaRepository repository;

    public void getDadosAndSave() {
        final String apiURL = "https://qiiw8bgxka.execute-api.us-east-2.amazonaws.com/acervo/biblioteca";
        System.out.println("Iniciando sincronizacao de dados Biblioteca");

        try {
            repository.deleteAll();
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<List<Biblioteca>> getAPI = restTemplate.exchange(
                    apiURL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Biblioteca>>(){});

            List<Biblioteca> dados = getAPI.getBody();

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
        try {
            List<Biblioteca> livros = repository.getLivrosByDisponibilidade();

            return livros.stream()
                    .map(biblioteca -> new BibliotecaDTO(
                            biblioteca.getId(),
                            biblioteca.getTitulo(),
                            biblioteca.getAutor(),
                            biblioteca.getAno(),
                            biblioteca.getStatus()))
                    .toList();
        } catch (Exception e) {
            throw new ApiException("Erro ao listar livros disponiveis", HttpStatus.BAD_REQUEST);
        }
    }

    public BibliotecaDTO getLivroById(Long id) {
        Biblioteca livro = repository.findById(id)
                .orElseThrow(() -> new ApiException("Discente não encontrado", HttpStatus.BAD_REQUEST));

        return new BibliotecaDTO(livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getAno(), livro.getStatus());
    }

    public void alterarStatusLivroIndisp(Long idLivro) {
        Biblioteca livro = repository.findById(idLivro)
                .orElseThrow(() -> new ApiException("Discente não encontrado", HttpStatus.BAD_REQUEST));

        livro.setStatus("Indisponível");

        repository.save(livro);
    }

    public void alterarStatusLivroDisp(Long idLivro) {
        Biblioteca livro = repository.findById(idLivro)
                .orElseThrow(() -> new ApiException("Discente não encontrado", HttpStatus.BAD_REQUEST));

        livro.setStatus("Disponivel");

        repository.save(livro);
    }


}
