package uz.pdp.onekhm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.onekhm.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}