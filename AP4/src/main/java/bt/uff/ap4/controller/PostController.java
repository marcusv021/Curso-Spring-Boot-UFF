package bt.uff.ap4.controller;


import bt.uff.ap4.modelo.Post;
import bt.uff.ap4.modelo.PostComUsuario;
import bt.uff.ap4.modelo.Usuario;
import bt.uff.ap4.repository.UsuarioRepository;
import bt.uff.ap4.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("post")
public class PostController {

    private final PostService postService;
    private final UsuarioRepository usuarioRepository;

    public PostController(PostService postService, UsuarioRepository usuarioRepository) {
        this.postService = postService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("{id}")
    public ModelAndView get(@PathVariable Long id){
        var mv = new ModelAndView("post/get");

        mv.addObject("post", postService.findObyComUsuarioById(id));

        return mv;
    }

    @GetMapping("new")
    public ModelAndView novo(){
        var mv = new ModelAndView("post/edit");

        //passa o Post puro, que tem o campo 'id' que o HTML procura
        mv.addObject("post", new Post(null, null, "", null, java.util.List.of()));

        return mv;
    }

    @PostMapping
    public String save(Post post){
        System.out.println("Salvando post com a mensagem: " + post.mensagem());

        //criamos o usuário de teste
        var usuario = usuarioRepository.save(new Usuario(
                null, "Teste", "Teste", 10, "35frg5/f5rf6"
        ));

        //remove nomes vazios ou nulos da lista de tags
        var tagsFiltradas = post.tags().stream()
                .filter(t -> t.nome() != null && !t.nome().trim().isEmpty())
                .toList();

        //cria o post com as tags limpas e o ID do usuário
        Post postProntoParaSalvar = new Post(
                post.id(),
                post.dataPostagem(),
                post.mensagem(),
                usuario.id(),
                tagsFiltradas
        );

        var postSalvo = postService.save(postProntoParaSalvar);

        return "redirect:/post/" + postSalvo.id();
    }

    @GetMapping("{id}/edit")
    public ModelAndView edit(@PathVariable Long id){
        var mv = new ModelAndView("post/edit");

        mv.addObject("post", postService.findObjById(id));

        return mv;
    }


    @GetMapping
    public ModelAndView list(){
        var mv = new ModelAndView("post/list");
        mv.addObject("posts", postService.findAll(Pageable.unpaged()));
        return mv;
    }


}
