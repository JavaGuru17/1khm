package uz.pdp.onekhm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.onekhm.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
