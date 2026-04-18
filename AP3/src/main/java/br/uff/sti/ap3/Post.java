package br.uff.sti.ap3;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Table(name="post", schema = "ap3")
public record Post(
        @Id Long id,
        @NotNull LocalDateTime dataPostagem,
        @Size(max=2000) String mensagem,
        @NotNull Long usuarioId,
        @MappedCollection(idColumn = "post_id", keyColumn = "ordem")
        List<Tag> tags
) {
}
