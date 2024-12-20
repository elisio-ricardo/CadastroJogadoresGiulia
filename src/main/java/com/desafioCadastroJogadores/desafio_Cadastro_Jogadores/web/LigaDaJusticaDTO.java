package com.desafioCadastroJogadores.desafio_Cadastro_Jogadores.web;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "liga_da_justica")
public record LigaDaJusticaDTO(
        //fazer uma chamada na uri, para entender melhor a deserialização
        @JacksonXmlProperty(localName = "codinomes") CodinomeDTO codinomes) implements CodinomeDTO{
        @Override
        public List<String> getCodinomes() {
                return codinomes.getCodinomes();
        }
}

record CodinomesDTO(
        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "codinome") List<String> codinomes
) {

}
