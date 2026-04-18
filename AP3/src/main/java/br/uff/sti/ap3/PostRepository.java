package br.uff.sti.ap3;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

public interface PostRepository extends CrudRepository<Post, Long> {
    //retorna os últimos 5 posts de um usuário
    @Query("SELECT * FROM ap3.post WHERE usuario_id = :usuarioId ORDER BY data_postagem DESC LIMIT 5")
    List<Post> buscarUltimos5DoUsuario(@Param("usuarioId") Long usuarioId);

    //retorna os últimos 10 posts que tenham a tag "rpg"
    //JOIN para cruzar a tabela de posts com a tabela de tags
    @Query("SELECT p.* FROM ap3.post p JOIN ap3.post_tag pt ON p.id = pt.post_id WHERE pt.nome = :tag ORDER BY p.data_postagem DESC LIMIT 10")
    List<Post> buscarUltimos10PorTag(@Param("tag") String tag);

    //busca todos os posts que tenham a palavra "orc"
    //LIKE para procurar a palavra no meio da frase
    @Query("SELECT * FROM ap3.post WHERE LOWER(mensagem) LIKE '%orc%'")
    List<Post> buscarPostsComIntrusos();

    //pega os últimos 15 posts usando Stream
    @Query("SELECT * FROM ap3.post ORDER BY data_postagem DESC LIMIT 15")
    Stream<Post> buscarUltimos15EmStream();
}
