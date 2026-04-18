package br.uff.sti.ap2;

import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

//a anotação avisa ao Spring para assumir o controle da classe e transformar em um Bean
@Component
public class LeitorJson {

    private final String jsonDeEntrada = """
            [
              { "nome": "Yara Guarani", "idade": 21, "cpf": "54291827305", "curso": "Ciências Biológicas" },
              { "nome": "Enzo Valentim de Arcanjo", "idade": 30, "cpf": "81274390512", "curso": "Astronomia" },
              { "nome": "Dagoberto Peixoto", "idade": 55, "cpf": "29304156788", "curso": "História" }
            ]
            """;
    @Loga
    public List<Pessoa> lerDados() throws Exception {
        ObjectMapper mapeador = new ObjectMapper();

        //converte a String com o JSON diretamente para uma lista de objetos Pessoa
        return mapeador.readValue(jsonDeEntrada, new TypeReference<List<Pessoa>>() {});
    }
}