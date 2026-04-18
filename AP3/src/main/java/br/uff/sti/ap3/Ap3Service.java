package br.uff.sti.ap3;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service

public class Ap3Service {

    public static final String URL_IMAGEM_1 = "https://fastly.picsum.photos/id/872/200/200.jpg?hmac=m0AwAUFkEiEz2KW58n6a5RVkKaClHNylfppYjE3a0v4";
    private final UsuarioDAO usuarioDAO;
    private final PostService postService;
    private final PostRepository postRepository;

    public Ap3Service(UsuarioDAO usuarioDAO, PostService postService, PostRepository postRepository) {
        this.usuarioDAO = usuarioDAO;
        this.postService = postService;
        this.postRepository = postRepository;
    }

    Faker faker = new Faker();
    @Transactional
    public void prepararBancoDeDados() {
        //inserção de dados aleatorios

        List<Usuario> listaUsuarios = new ArrayList<>();

        //cria 5 usuarios diferentes
        String[] nomesUsuarios = {"Arthur", "Guilherme", "Daniel", "Marcio", "Leonardo"};

        for (int i = 0; i < 5; i++) {
            //cria e já salva no banco usando o DAO
            Usuario u = new Usuario(null, "user_" + i, nomesUsuarios[i], faker.number().numberBetween(20, 60), URL_IMAGEM_1);
            Usuario usuarioSalvo = usuarioDAO.save(u);
            listaUsuarios.add(usuarioSalvo); //guarda na lista para usar como autores dos posts depois
        }

        //cria 30 posts aleatórios
        String[] possiveisTags = {"aventura", "tecnologia", "rpg", "politica", "esporte", "filme"};
        //lista de palavras aleatorias para servir de base de conteudo para criar os posts
        String[] dicionario = {"espada", "escudo", "orc", "castelo", "dragão", "poção", "taberna", "cavaleiro", "mago", "floresta", "ouro", "perigo", "missão", "reino"};

        for (int i = 0; i < 30; i++) {
            //sorteia um dos 5 usuários para ser o autor deste post
            Usuario autorSorteado = listaUsuarios.get(faker.random().nextInt(5));

            //sorteia uma data no passado
            LocalDateTime dataAleatoria = LocalDateTime.now()
                    .minusDays(faker.random().nextInt(60))
                    .minusHours(faker.random().nextInt(24));

            //sorteia a quantidade de palavras do post
            int quantidadePalavras = faker.random().nextInt(5, 15);
            StringBuilder mensagemAleatoria = new StringBuilder();

            for (int j = 0; j < quantidadePalavras; j++) {
                //sorteia uma palavra do dicionário e adiciona no texto
                String palavraSorteada = dicionario[faker.random().nextInt(dicionario.length)];
                mensagemAleatoria.append(palavraSorteada).append(" ");
            }

            //gera o texto do post
            String mensagem = mensagemAleatoria.toString().trim();

            //escolhe a tag do post
            List<String> tagsDoPost = new ArrayList<>();
            String tagSorteada = possiveisTags[faker.random().nextInt(possiveisTags.length)];
            tagsDoPost.add(tagSorteada);

            //garante uma distribuição da tag "rpg" a cada 3 posts
            if (i % 3 == 0 && !tagSorteada.equals("rpg")) {
                tagsDoPost.add("rpg");
            }

            //monta o Post e pede pro PostService salvar
            Post novoPost = new Post(
                    null,
                    dataAleatoria,
                    mensagem,
                    autorSorteado.id(),
                    Tag.of(tagsDoPost.toArray(new String[0]))
            );

            postService.save(novoPost);
        }
    }

    @Transactional(readOnly = true)
    public void testarConsultas() {
        System.out.println("\nItem 2: Últimos 5 posts de um usuário aleatório:");
        Long idSorteado = (long) faker.random().nextInt(1, 5);
        System.out.println("Consultando últimos posts do ID sorteado: " + idSorteado);
        postRepository.buscarUltimos5DoUsuario(idSorteado).forEach(post -> {
                    var autor = usuarioDAO.findById(post.usuarioId()).orElseThrow();
                    System.out.println("[" + autor.nome() + "]: " + post.mensagem());
                });

        System.out.println("\nItem 3: Últimos 10 posts com a tag 'rpg':");
        postRepository.buscarUltimos10PorTag("rpg")
                .forEach(post -> System.out.println("Post RPG: " + post.mensagem()));

        System.out.println("\nItem 4: Posts com a palavra 'orc':");
        postRepository.buscarPostsComIntrusos()
                .forEach(post -> System.out.println("Post com 'orc': " + post.mensagem()));

        System.out.println("\nItem 5: Últimos 15 posts via Stream:");
        try (Stream<Post> streamDePosts = postRepository.buscarUltimos15EmStream()) {
            streamDePosts.forEach(post -> System.out.println("Stream Post: " + post.mensagem()));
        }
    }
}
