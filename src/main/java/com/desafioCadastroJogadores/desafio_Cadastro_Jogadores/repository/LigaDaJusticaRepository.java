package com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.repository;

import com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.model.GrupoCodinome;
import com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.web.CodinomeDTO;
import com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.web.LigaDaJusticaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

import java.util.List;

@Repository
public class LigaDaJusticaRepository implements CodinomeRepository {
    @Override
    public CodinomeDTO buscarCodinomes() throws JsonProcessingException {
        var codinomes = RestClient.builder()
                .baseUrl(GrupoCodinome.LIGA_DA_JUSTICA.getUri())
                .build()
                .get()
                .retrieve()
                .body(String.class);

        var xmlMapper = new XmlMapper();
        var ligaDaJustica = xmlMapper.readValue(codinomes, LigaDaJusticaDTO.class);
        return ligaDaJustica;
    }
}
