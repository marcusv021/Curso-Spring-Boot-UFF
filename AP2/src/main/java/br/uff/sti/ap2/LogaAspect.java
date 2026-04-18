package br.uff.sti.ap2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@Aspect diz que essa classe é um interceptador e @Component entrega pro Spring gerenciar
@Aspect
@Component
public class LogaAspect {

    //fica vigiando qualquer método que tenha a anotação @Loga
    @Around("@annotation(Loga)")
    public Object gravarLog(ProceedingJoinPoint joinPoint) throws Throwable {

        //extraindo as informações
        String nomeMetodo = joinPoint.getSignature().getName();
        String nomeClasse = joinPoint.getTarget().getClass().getSimpleName();
        String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        //manda o método original continuar e terminar de executar
        Object resultado = joinPoint.proceed();

        //grava o Log em um arquivo CSV
        try (FileWriter fw = new FileWriter("log_auditoria.csv", true);
             PrintWriter pw = new PrintWriter(fw)) {

            //grava no formato correto
            pw.println(nomeClasse + "," + nomeMetodo + "," + dataHora);

        } catch (Exception e) {
            System.err.println("Erro ao gravar log de auditoria: " + e.getMessage());
        }

        return resultado;
    }
}