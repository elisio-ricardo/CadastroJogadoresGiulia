package com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.service;


import com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.exception.GrupocodinomeIndisponivelException;
import com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.model.GrupoCodinome;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodinomeService {

    private final CodinomeRepositoryFactory codinomeRepositoryFactory;

    public CodinomeService(CodinomeRepositoryFactory codinomeRepositoryFactory) {
        this.codinomeRepositoryFactory = codinomeRepositoryFactory;
    }

    public String gerarCodinome(GrupoCodinome grupoCodinome, List<String> codinomeEmUso) throws Exception {
        var codinomeDisponiveis = listaCodinomesDisponiveis(grupoCodinome, codinomeEmUso);
        if (codinomeDisponiveis.isEmpty())
            throw new GrupocodinomeIndisponivelException();

        var codinomeSorteado = sortearCodinome(codinomeDisponiveis);
        return codinomeSorteado;
    }


    private List<String> listaCodinomesDisponiveis(GrupoCodinome grupoCodinome, List<String> codinomeEmUso) throws JsonProcessingException {
        var codinomes = buscarCodinomes(grupoCodinome);

        var codinomesDisponiveis = codinomes
                .stream()
                .filter(codinome -> !codinomeEmUso.contains(codinome))
                .toList();

        return codinomesDisponiveis;
    }

    private List<String> buscarCodinomes(GrupoCodinome grupoCodinome) throws JsonProcessingException {
        var codinomeRepository = codinomeRepositoryFactory.create(grupoCodinome);
        return codinomeRepository.buscarCodinomes().getCodinomes();
    }

    private String sortearCodinome(List<String> codinomeDisponiveis) {
        return codinomeDisponiveis
                .get((int) (Math.random() * codinomeDisponiveis.size()));
        //Logica sorteio
        //0.0(random) * 10 = 0(pos)
        //0.1(random) * 10(size) = 1(pos)
        //0.9(random) * 10(size) =9(pos)
    }


}
