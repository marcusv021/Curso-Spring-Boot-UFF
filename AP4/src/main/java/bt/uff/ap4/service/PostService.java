package bt.uff.ap4.service;

import bt.uff.ap4.modelo.Post;
import bt.uff.ap4.modelo.PostComUsuario;
import bt.uff.ap4.repository.PostRepository;
import bt.uff.ap4.repository.UsuarioRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;


@Service
public class PostService {

    private final Validator validator;

    private final PostRepository postRepository;

    private final UsuarioRepository usuarioRepository;

    public PostService(Validator validator, PostRepository postRepository, UsuarioRepository usuarioRepository) {
        this.validator = validator;
        this.postRepository = postRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Post save(Post post){
        assert post.id() == null;

        post = new Post(
                post.id(),
                java.time.LocalDateTime.now(),
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

    @Transactional(readOnly = true)
    public Post findObjById(long id) {
        return postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Post com id %d não encontrado".formatted(id)));
    }

    @Transactional(readOnly = true)
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public PostComUsuario findObyComUsuarioById(long id){
        var post = findObjById(id);

        var usuario = usuarioRepository.findById(post.usuarioId()).orElseThrow();

        return new PostComUsuario(
                post,
                usuario
        );
    }
}
