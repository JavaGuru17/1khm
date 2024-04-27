package uz.pdp.onekhm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.onekhm.domain.Info;

@Repository
public interface InfoRepository extends JpaRepository<Info,Long> {
}
