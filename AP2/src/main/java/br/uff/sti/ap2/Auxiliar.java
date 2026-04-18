package br.uff.sti.ap2;

import org.springframework.stereotype.Component;

@Component
public class Auxiliar {

    private final GravadorDeNotas gravador;

    public Auxiliar(GravadorDeNotas gravador) {
        this.gravador = gravador;
    }

    @AbreCsv(arquivo = "boletim_final.csv")
    public void ajudarNaGravacao() {
        gravador.gravarNota("Enzo Valentim", "8.5");
    }
}