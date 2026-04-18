package br.uff.sti.ap3;

import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.stream.Stream;

@Table(name="post_tag", schema = "ap3")
public record Tag(
        String nome
) {
    public static List<Tag> of(String... strs) {
        return Stream.of(strs)
                .map(Tag::new)
                .toList();
            }
}
