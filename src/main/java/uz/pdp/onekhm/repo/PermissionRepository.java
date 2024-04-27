package uz.pdp.onekhm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.onekhm.domain.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
