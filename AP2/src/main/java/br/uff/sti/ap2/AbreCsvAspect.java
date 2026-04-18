package br.uff.sti.ap2;

import de.siegmar.fastcsv.writer.CsvWriter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.FileWriter;

@Aspect
@Component
public class AbreCsvAspect {

    @Around("@annotation(anotacao)")
    public Object gerenciarArquivo(ProceedingJoinPoint joinPoint, AbreCsv anotacao) throws Throwable {

        int nivelAtual = ContextoCsv.profundidade.get();
        ContextoCsv.profundidade.set(nivelAtual + 1); //avisa que entramos em mais um método anotado

        boolean fuiEuQueAbri = false;

        //se for a primeira anotação da cadeia, abre o arquivo e coloca na gaveta
        if (nivelAtual == 0) {
            FileWriter fw = new FileWriter(anotacao.arquivo(), true);
            CsvWriter escritor = CsvWriter.builder().fieldSeparator(',').build(fw);
            ContextoCsv.escritorAtual.set(escritor);
            fuiEuQueAbri = true;
        }

        try {
            //deixa o método original rodar, e qualquer outro método que ele chame lá dentro
            return joinPoint.proceed();
        } finally {
            //quando o método terminar, diminui o nível
            int nivelNovo = ContextoCsv.profundidade.get() - 1;
            ContextoCsv.profundidade.set(nivelNovo);

            //apenas fecha o arquivo quando o nível volta a zero
            if (fuiEuQueAbri && nivelNovo == 0) {
                CsvWriter escritor = ContextoCsv.escritorAtual.get();
                if (escritor != null) {
                    escritor.close();
                }
                ContextoCsv.escritorAtual.remove();//limpa a gaveta
                ContextoCsv.profundidade.remove();
            }
        }
    }
}