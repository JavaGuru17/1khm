package uz.pdp.onekhm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.onekhm.domain.Permission;
import uz.pdp.onekhm.exception.AlreadyExistsException;
import uz.pdp.onekhm.exception.NotFoundException;
import uz.pdp.onekhm.repo.PermissionRepository;
import uz.pdp.onekhm.service.PermissionService;
import uz.pdp.onekhm.utils.Validation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    @Override
    public Permission save(Permission permission) {
        if (permissionRepository.existsById(permission.getId()))
            throw new AlreadyExistsException("Permission with id " + permission.getId());

        if (permissionRepository.findByCode(permission.getCode()).isPresent())
            throw new AlreadyExistsException("Permission already exists");

        return permissionRepository.save(permission);
    }

    @Override
    public Permission update(Permission permission) {
        Permission existingPermission = permissionRepository.findById(permission.getId())
                .orElseThrow(() -> new NotFoundException("Permission with id " + permission.getId()));

        Permission updatedPermission = Permission.builder()
                .id(existingPermission.getId())
                .code(Validation.requireNonNullElse(permission.getCode(), existingPermission.getCode()))
                .description(Validation.requireNonNullElse(permission.getDescription(), existingPermission.getDescription()))
                .build();

        return permissionRepository.save(updatedPermission);
    }

    @Override
    public void delete(Long id) {
        if (!permissionRepository.existsById(id))
            throw new NotFoundException("Permission with id " + id);
        permissionRepository.deleteById(id);
    }

    @Override
    public Permission getById(Long id) {
        if (!permissionRepository.existsById(id))
            throw new NotFoundException("Permission with id " + id);
        return permissionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Permission with id " + id));
    }

    @Override
    public List<Permission> getAll() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission findByCode(String code) {
        return permissionRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Permission with code " + code));
    }
}
