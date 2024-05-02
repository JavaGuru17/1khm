package uz.pdp.onekhm.service;

import org.springframework.stereotype.Service;
import uz.pdp.onekhm.domain.Permission;
import uz.pdp.onekhm.dto.request.PermissionDto;

import java.util.List;

@Service
public interface PermissionService {
    PermissionDto save(PermissionDto permissionDto);
    PermissionDto update(PermissionDto permissionDto);
    void delete(Long id);
    Permission getById(Long id);
    List<Permission> getAll();
    Permission findByCode(String code);
    void addToRole(Long roleId, Long permissionId);
    void removeFromRole(Long roleId, Long permissionId);
}
