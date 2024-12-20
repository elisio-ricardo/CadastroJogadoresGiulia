package com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.service;


import com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.model.GrupoCodinome;
import com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.model.Jogador;
import com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.repository.JogadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogadorService {

    private final JogadorRepository jogadorRepository;
    private final CodinomeService codinomeService;

    public JogadorService(JogadorRepository jogadorRepository, CodinomeService codinomeService) {
        this.jogadorRepository = jogadorRepository;
        this.codinomeService = codinomeService;
    }

    public Jogador registrarJogador(Jogador jogador) throws Exception {
        var codinomeEmUso = listarCodinomeEmUso(jogador.grupoCodinome());
        var novoCodinome = codinomeService.gerarCodinome(jogador.grupoCodinome(), codinomeEmUso);

        var novoJogador = new Jogador(jogador.nome(),
                jogador.email(), jogador.telefone(), novoCodinome, jogador.grupoCodinome());

        return jogadorRepository.salvar(novoJogador);
    }

    public List<Jogador> listarJogadores() {
        return jogadorRepository.listarJogadores();
    }

    private List<String> listarCodinomeEmUso(GrupoCodinome grupoCodinome) {
        return jogadorRepository.listarCodinomesPorGrupo(grupoCodinome);
    }
}
