package uz.pdp.onekhm.service;

import org.springframework.stereotype.Service;
import uz.pdp.onekhm.domain.Permission;

import java.util.List;

@Service
public interface PermissionService {
    Permission save(Permission permission);
    Permission update(Permission permission);
    void delete(Long id);
    Permission getById(Long id);
    List<Permission> getAll();
    Permission findByCode(String code);
}
