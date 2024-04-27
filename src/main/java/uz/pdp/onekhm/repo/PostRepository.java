package uz.pdp.onekhm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.onekhm.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
}
