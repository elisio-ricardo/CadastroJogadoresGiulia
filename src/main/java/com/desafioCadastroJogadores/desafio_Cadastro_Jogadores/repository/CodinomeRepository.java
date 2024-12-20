package com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.repository;

import com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.web.CodinomeDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface CodinomeRepository {

    CodinomeDTO buscarCodinomes() throws JsonProcessingException;

}
