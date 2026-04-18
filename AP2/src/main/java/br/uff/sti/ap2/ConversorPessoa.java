package br.uff.sti.ap2;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConversorPessoa {

    //recebe a lista de objetos Pessoa e transforma em uma lista de linhas de texto
    public List<String[]> converterParaFormatoCsv(List<Pessoa> pessoas) {
        List<String[]> linhasCsv = new ArrayList<>();

        //criando o cabeçalho do csv
        linhasCsv.add(new String[]{"nome", "idade", "cpf", "curso"});

        //convertendo cada pessoa da lista
        for (Pessoa p : pessoas) {
            linhasCsv.add(new String[]{
                    p.nome(),
                    String.valueOf(p.idade()), //convertendo o número da idade de volta para texto
                    p.cpf(),
                    p.curso()
            });
        }
        return linhasCsv;
    }
}