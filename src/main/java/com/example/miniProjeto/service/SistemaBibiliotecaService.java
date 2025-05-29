package com.example.miniProjeto.service;

import com.example.miniProjeto.dto.BibliotecaDTO;
import com.example.miniProjeto.dto.DiscenteDTO;
import com.example.miniProjeto.dto.SistemaBibliotecaDTO;
import com.example.miniProjeto.exception.ApiException;
import com.example.miniProjeto.model.SistemaBiblioteca;
import com.example.miniProjeto.repository.SistemaBibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SistemaBibiliotecaService {

    @Autowired
    SistemaBibliotecaRepository repository;

    @Autowired
    DiscenteService discenteService;

    @Autowired
    BibliotecaService bibliotecaService;


    public SistemaBibliotecaDTO alugarLivro(Long idAluno, Long idLivro) {

        DiscenteDTO aluno = getDiscenteAtivo(idAluno);
        BibliotecaDTO livro = getLivroDisponivel(idLivro);


        try {
            SistemaBiblioteca livroAlugado = new SistemaBiblioteca();
            livroAlugado.setIdDiscente(aluno.getId());
            livroAlugado.setIdLivro(livro.getId());
            livroAlugado.setNomeLivro(livro.getTitulo());
            livroAlugado.setDataAlocado(LocalDate.now());
            livroAlugado.setDataDevolucao(LocalDate.now().plusDays(14));

            repository.save(livroAlugado);
            bibliotecaService.alterarStatusLivroIndisp(idLivro);

            return new SistemaBibliotecaDTO(livroAlugado.getId(),livroAlugado.getIdDiscente(), livroAlugado.getIdLivro() ,
                    livroAlugado.getNomeLivro(), livroAlugado.getDataAlocado(), livroAlugado.getDataDevolucao());
        } catch (Exception e) {
            throw new ApiException("Não foi possível alugar Livro, erro no sistema", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<SistemaBibliotecaDTO> getLivrosAlugadoAluno (Long idAluno) {
        try {
            List<SistemaBiblioteca> livros = repository.findByIdDiscente(idAluno);

            return livros.stream()
                    .map(sistemaB -> new SistemaBibliotecaDTO(
                            sistemaB.getId(),
                            sistemaB.getIdDiscente(),
                            sistemaB.getIdLivro(),
                            sistemaB.getNomeLivro(),
                            sistemaB.getDataAlocado(),
                            sistemaB.getDataDevolucao()))
                    .toList();

        } catch (Exception e) {
            throw new ApiException("Não foi possível listar livros alugados pelo aluno", HttpStatus.BAD_REQUEST);
        }
    }

    public String cancelarAluguel(Long idRegistro) {
        try {
            SistemaBiblioteca registro = repository.findById(idRegistro)
                    .orElseThrow(() -> new ApiException("Registro não encontrado", HttpStatus.BAD_REQUEST));

            repository.deleteById(idRegistro);

            bibliotecaService.alterarStatusLivroDisp(registro.getIdLivro());

            return "Cancelado aluguel do livro com registro: " + idRegistro ;
        } catch (Exception e) {
            throw new ApiException("Não foi possível cancelar aluguel do livro", HttpStatus.BAD_REQUEST);
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

    private BibliotecaDTO getLivroDisponivel(Long idLivro) {
        BibliotecaDTO biblioteca = bibliotecaService.getLivroById(idLivro);

        if (biblioteca.getStatus().equals("Disponível")) {
            return biblioteca;
        } else {
            throw new ApiException("Livro já está alugado!", HttpStatus.BAD_REQUEST);
        }
    }
}
