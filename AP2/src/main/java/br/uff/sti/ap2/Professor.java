package br.uff.sti.ap2;

import org.springframework.stereotype.Component;

@Component
public class Professor {

    private final GravadorDeNotas gravador;
    private final Auxiliar auxiliar;

    public Professor(GravadorDeNotas gravador, Auxiliar auxiliar) {
        this.gravador = gravador;
        this.auxiliar = auxiliar;
    }

    @AbreCsv(arquivo = "boletim_final.csv")
    public void registrarTodasAsNotas() {
        System.out.println("Professor: Lançando a primeira nota...");
        gravador.gravarNota("Yara Guarani", "10.0");

        auxiliar.ajudarNaGravacao();
    }
}