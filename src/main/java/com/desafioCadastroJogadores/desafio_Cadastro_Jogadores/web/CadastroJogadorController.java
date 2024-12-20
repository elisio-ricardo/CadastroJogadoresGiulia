package com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.web;


import com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.exception.GrupocodinomeIndisponivelException;
import com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.model.GrupoCodinome;
import com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.model.Jogador;
import com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.service.JogadorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class CadastroJogadorController {

    private final JogadorService jogadorService;

    public CadastroJogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @GetMapping("/cadastro-jogador")
    public String paginaCadastroJogador(Model model) {
        return getViewAndModel(model, new Jogador(null, null, null, null, null));
    }


    @PostMapping("/cadastro-jogador")
    public String cadastroJogador(@ModelAttribute @Valid Jogador jogador, BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
            return getViewAndModel(model, jogador);
        }
        try {
            jogadorService.registrarJogador(jogador);
            return "redirect:/listagem_jogadores";
        } catch (GrupocodinomeIndisponivelException e) {
            result.rejectValue("grupoCodinome", "grupoCodinomeIndisponivel", e.getMessage());
            return getViewAndModel(model, jogador);
        }
    }

    private static String getViewAndModel(Model model, Jogador jogador) {
        model.addAttribute("jogador", jogador);
        model.addAttribute("gruposCodinomes", GrupoCodinome.values());
        return "cadastro_jogador";
    }


}
