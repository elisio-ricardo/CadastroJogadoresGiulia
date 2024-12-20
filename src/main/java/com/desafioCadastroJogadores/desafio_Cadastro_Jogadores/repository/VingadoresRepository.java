package com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.repository;

import com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.model.GrupoCodinome;
import com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.web.CodinomeDTO;
import com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.web.VingadoresDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;


import java.util.List;


@Repository
public class VingadoresRepository implements CodinomeRepository {

    @Override
    public CodinomeDTO buscarCodinomes() throws JsonProcessingException {
        //essa chamada retorna um text/plain, se qser conferir faz uma chamada com a url no browser
        var codinomes = RestClient
                .builder()
                .baseUrl(GrupoCodinome.VINGADORES.getUri())
                .build()
                .get()
                .retrieve()
                .body(String.class);
        var objectMapper = new ObjectMapper();
        var vingadores = objectMapper.readValue(codinomes, VingadoresDTO.class);

        return vingadores;
    }
}
