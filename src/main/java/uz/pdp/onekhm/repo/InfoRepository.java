package uz.pdp.onekhm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.onekhm.domain.Info;

@Repository
public interface InfoRepository extends JpaRepository<Info,Long> {
    @Query("SELECT i FROM Info i WHERE i.id = 1")
    Info getInfo();
}