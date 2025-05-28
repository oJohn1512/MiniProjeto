package com.example.miniProjeto.service;

import com.example.miniProjeto.exception.ApiException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumacaoAPIService {

    @Autowired
    BibliotecaService bibliotecaService;

    @Autowired
    DisciplinasService disciplinasService;

    @Autowired
    DiscenteService discenteService;

    @PostConstruct
    public void consumirAPIs() {
        System.out.println("Iniciando sincronização paralela:");

        try {
            bibliotecaService.getDadosAndSave();
            discenteService.getDadosAndSave();
            disciplinasService.getDadosAndSave();

            System.out.println("Todos as APIs consumidas com sucesso!");
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Não foi possivel consumir todas as APIs", e.getCause());
        }

    }
}
