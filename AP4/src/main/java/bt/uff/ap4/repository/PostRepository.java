package bt.uff.ap4.repository;

import bt.uff.ap4.modelo.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, Long>,
        CrudRepository<Post, Long> {
}
