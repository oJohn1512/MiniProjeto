package com.example.miniProjeto.service;

import com.example.miniProjeto.Exception.ApiException;
import com.example.miniProjeto.repository.BibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ConsumacaoAPIService implements CommandLineRunner {

    @Autowired
    private DiscenteService discenteService;

    @Autowired
    private DisciplinasService disciplinasService;

    @Autowired
    private BibliotecaService bibliotecaService;

    @Override
    public void run(String... args) {
        System.out.println("Iniciando sincronização paralela:");

        try {
            CompletableFuture<Void> consumacao1 = CompletableFuture.runAsync(() -> discenteService.getDadosAndSave());
            CompletableFuture<Void> consumacao2 = CompletableFuture.runAsync(() -> disciplinasService.getDadosAndSave());
            CompletableFuture<Void> consumacao3 = CompletableFuture.runAsync(() -> bibliotecaService.getDadosAndSave());


            CompletableFuture.allOf(consumacao1, consumacao2, consumacao3).join();
            System.out.println("Todos as APIs consumidas com sucesso!");

        } catch (ApiException e) {
          throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Não foi possivel consumir todas as APIs", e.getCause());
        }

    }
}
