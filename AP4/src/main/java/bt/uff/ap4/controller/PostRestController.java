package bt.uff.ap4.controller;

import bt.uff.ap4.modelo.Post;
import bt.uff.ap4.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/post")
public class PostRestController {

    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("{id}")
    public Post get(@PathVariable long id){
        return this.postService.findObjById(id);
    }

    @GetMapping
    public List<Post> list(@PageableDefault Pageable pageable){
        return this.postService.findAll(pageable).getContent();
    }
}
