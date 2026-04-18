package br.uff.sti.ap2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Ap2Application {

    public static void main(String[] args) {

        try (var context = SpringApplication.run(Ap2Application.class, args)) {

            //spring injeta as dependências prontas (IoC)
            LeitorJson leitor = context.getBean(LeitorJson.class);
            ConversorPessoa conversor = context.getBean(ConversorPessoa.class);
            EscritorCsv escritor = context.getBean(EscritorCsv.class);

            //orquestra o fluxo da aplicação
            List<Pessoa> pessoas = leitor.lerDados();
            List<String[]> linhasCsv = conversor.converterParaFormatoCsv(pessoas);
            escritor.gravar(linhasCsv);

            //para o exercício extra
            Professor professor = context.getBean(Professor.class);
            professor.registrarTodasAsNotas();

        } catch (Exception e) {
            System.err.println("Falha na execução principal: " + e.getMessage());
        }
    }
}