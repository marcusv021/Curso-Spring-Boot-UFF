package br.uff.sti.ap2;

import de.siegmar.fastcsv.writer.CsvWriter;

public class ContextoCsv {
    //guarda o arquivo aberto para ser usado por outros Beans
    public static final ThreadLocal<CsvWriter> escritorAtual = new ThreadLocal<>();

    //conta quantos métodos anotados estão dentro um do outro
    public static final ThreadLocal<Integer> profundidade = ThreadLocal.withInitial(() -> 0);
}