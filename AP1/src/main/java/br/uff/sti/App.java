package br.uff.sti;

import tools.jackson.databind.ObjectMapper;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRecord;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        // 1. Variável String contendo o CSV
        String dadosBrutosCsv = """
                nome, idade, cpf, curso
                Yara Guarani, 21, 54291827305, Ciências Biológicas
                Enzo Valentim de Arcanjo, 30, 81274390512, Astronomia
                Dagoberto Peixoto, 55, 29304156788, História
                Ximena Luzia das Neves, 24, 60412398744, Design de Moda
                Ubirajara Pires, 42, 15928374600, Agronomia
                """;

        List<Pessoa> listaPessoas = new ArrayList<>();

        try {
            //Cria o objeto responsavel por configurar a interpretação do arquivo CSV
            CsvReader.CsvReaderBuilder construtorDoLeitor = CsvReader.builder();
            //Avisa que as colunas são separadas por virgula
            construtorDoLeitor.fieldSeparator(',');

            //Cria o leitor
            CsvReader<CsvRecord> leitorCsv = construtorDoLeitor.ofCsvRecord(dadosBrutosCsv);

            //Verifica se a linha é o cabeçalho
            boolean isPrimeiraLinha = true;

            //itera sobre cada linha
            for (CsvRecord linha : leitorCsv) {

                //ignora cabeçalho
                if (isPrimeiraLinha) {
                    isPrimeiraLinha = false;
                    continue;
                }

                //lê cada coluna e remove os espaços em branco que sobraram após a vírgula
                String nome = linha.getField(0).trim();
                int idade = Integer.parseInt(linha.getField(1).trim()); //converte a string para int
                String cpf = linha.getField(2).trim();
                String curso = linha.getField(3).trim();

                listaPessoas.add(new Pessoa(nome, idade, cpf, curso));
            }

            //transformando a lista em JSON
            ObjectMapper objectMapper = new ObjectMapper();

            //formata o JSON
            String jsonOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(listaPessoas);

            //imprime na tela o JSON final
            System.out.println(jsonOutput);

        } catch (Exception e) {
            System.err.println("Erro ao processar os dados: " + e.getMessage());
        }
    }
}