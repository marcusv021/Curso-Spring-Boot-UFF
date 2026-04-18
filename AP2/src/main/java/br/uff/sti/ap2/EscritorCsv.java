package br.uff.sti.ap2;

import de.siegmar.fastcsv.writer.CsvWriter;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.util.List;

@Component
public class EscritorCsv {

    @Loga
    public void gravar(List<String[]> linhas) {

        try (FileWriter fileWriter = new FileWriter("saida.csv");
             CsvWriter csvWriter = CsvWriter.builder().fieldSeparator(',').build(fileWriter)) {

            for (String[] linha : linhas) {
                csvWriter.writeRecord(linha);
            }
            System.out.println("Arquivo 'saida.csv' foi gerado com sucesso na pasta raiz do projeto.");

        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao gravar o arquivo: " + e.getMessage());
        }
    }
}