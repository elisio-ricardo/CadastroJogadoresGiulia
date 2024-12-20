package com.desafioCadastroJogadores.desafio_Cadastro_Jogadores;


import com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.model.GrupoCodinome;
import com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.model.Jogador;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;


@SpringBootTest
@AutoConfigureMockMvc
public class DesafioCadastroJogadoresIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void cadastrarListarJogadoresSucesso() throws Exception {
        var jogador = new Jogador("test", "test@test.com",
                "12345", null, GrupoCodinome.VINGADORES);

        mockMvc
                .perform(post("/cadastro-jogador")
                        .param("nome", jogador.nome())
                        .param("email", jogador.email())
                        .param("telefone", jogador.telefone())
                        .param("grupoCodinome", jogador.grupoCodinome().name()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/listagem_jogadores"));

        mockMvc
                .perform(get("/listagem-jogadores"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("listagem_jogadores"))
                .andExpect(model().attribute("jogadores", hasSize(1)))
                .andExpect(model().attribute("jogadores", contains(allOf(
                        hasToString(containsString(jogador.nome())),
                        hasToString(containsString(jogador.email())),
                        hasToString(containsString(jogador.telefone())),
                        hasToString(containsString(jogador.grupoCodinome().name()))
                ))));
    }


}
