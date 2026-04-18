package br.uff.sti.ap3;

import jakarta.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Validator;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class PostService {

    private final Validator validator;

    private final PostRepository postRepository;

    public PostService(Validator validator, PostRepository postRepository) {
        this.validator = validator;
        this.postRepository = postRepository;
    }

    //garante que a operação no banco seja atomica e não deixe o banco "sujo" em caso de erro
    @Transactional
    public Post save(Post post){
        assert post.id() == null;

        //cria um Post novo copiando os dados do antigo, mas colocando a data de agora
        post = new Post(
                post.id(),
                LocalDateTime.now(),
                post.mensagem(),
                post.usuarioId(),
                post.tags()
        );

        final Set<ConstraintViolation<Post>> violations = validator.validate(post);

        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Objeto inválido: " + violations);
        }

        return postRepository.save(post);
    }
}
