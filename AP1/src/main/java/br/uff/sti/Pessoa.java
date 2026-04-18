package br.uff.sti;

import lombok.With;

@With
public record Pessoa(
        String nome,
        int idade,
        String cpf,
        String curso
) {
}