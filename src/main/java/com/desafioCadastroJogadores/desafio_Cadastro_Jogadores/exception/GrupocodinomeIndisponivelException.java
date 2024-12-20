package com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.exception;

public class GrupocodinomeIndisponivelException extends IllegalArgumentException {

    public GrupocodinomeIndisponivelException() {
        super("Não há condinomes disponiveis para o grupo selecionado");
    }
}
