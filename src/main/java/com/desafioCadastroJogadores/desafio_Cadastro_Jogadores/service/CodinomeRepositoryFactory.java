package com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.service;


import com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.model.GrupoCodinome;
import com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.repository.CodinomeRepository;
import com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.repository.LigaDaJusticaRepository;
import com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.repository.VingadoresRepository;
import org.springframework.stereotype.Component;

@Component
public class CodinomeRepositoryFactory {
 // classe com padroes de projeto strategy, polimorfismo, factory
    private final LigaDaJusticaRepository ligaDaJusticaRepository;
    private final VingadoresRepository vingadoresRepository;

    public CodinomeRepositoryFactory(LigaDaJusticaRepository ligaDaJusticaRepository, VingadoresRepository vingadoresRepository) {
        this.ligaDaJusticaRepository = ligaDaJusticaRepository;
        this.vingadoresRepository = vingadoresRepository;
    }

    public CodinomeRepository create(GrupoCodinome grupoCodinome) {
        return switch (grupoCodinome) {
            case LIGA_DA_JUSTICA -> ligaDaJusticaRepository;
            case VINGADORES -> vingadoresRepository;
        };
    }

}
