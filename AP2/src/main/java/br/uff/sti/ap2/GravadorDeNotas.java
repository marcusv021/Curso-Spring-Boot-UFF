package br.uff.sti.ap2;

import de.siegmar.fastcsv.writer.CsvWriter;
import org.springframework.stereotype.Component;

@Component
public class GravadorDeNotas {

    public void gravarNota(String aluno, String nota) {

        //vai buscar o arquivo que o Aspecto abriu
        CsvWriter escritor = ContextoCsv.escritorAtual.get();

        if (escritor != null) {
            escritor.writeRecord(new String[]{aluno, nota});
        } else {
            System.err.println("Erro: Não há nenhum CSV aberto.");
        }
    }
}