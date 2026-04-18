package bt.uff.ap4.modelo;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name="usuario")
public record Usuario(
        @Id Long id,
        @NotNull String username,
        @NotNull String nome,
        int idade,
        @NotNull String urlImagem
) {
}
