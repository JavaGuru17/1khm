package uz.pdp.onekhm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.onekhm.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
