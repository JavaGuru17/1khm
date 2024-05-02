package uz.pdp.onekhm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.onekhm.domain.Permission;
import uz.pdp.onekhm.domain.Role;
import uz.pdp.onekhm.dto.request.PermissionDto;
import uz.pdp.onekhm.exception.AlreadyExistsException;
import uz.pdp.onekhm.exception.NotFoundException;
import uz.pdp.onekhm.mapper.PermissionMapper;
import uz.pdp.onekhm.repo.PermissionRepository;
import uz.pdp.onekhm.repo.RoleRepository;
import uz.pdp.onekhm.service.PermissionService;
import uz.pdp.onekhm.utils.Validation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final PermissionMapper permissionMapper;

    @Override
    public PermissionDto save(PermissionDto permissionDto) {
        if (permissionRepository.findByCode(permissionDto.getCode().toUpperCase()).isPresent())
            throw new AlreadyExistsException("Permission");

        permissionRepository.save(permissionMapper.toEntity(permissionDto));

        return permissionDto;
    }

    @Override
    public PermissionDto update(PermissionDto permissionDto) {
        Permission existingPermission = permissionRepository.findById(permissionDto.getId())
                .orElseThrow(() -> new NotFoundException("Permission with id " + permissionDto.getId()));

        Permission updatedPermission = Permission.builder()
                .id(existingPermission.getId())
                .code(Validation.requireNonNullElse(permissionDto.getCode().toUpperCase(), existingPermission.getCode()))
                .description(Validation.requireNonNullElse(permissionDto.getDescription(), existingPermission.getDescription()))
                .build();

        return permissionMapper.toDto(permissionRepository.save(updatedPermission));
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

    @Override
    public void addToRole(Long roleId, Long permissionId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("Role with id " + roleId));
        Permission permission = permissionRepository.findById(permissionId).orElseThrow(() -> new NotFoundException("Permission with id " + permissionId));
        role.getPermissions().add(permission);
        roleRepository.save(role);
    }

    @Override
    public void removeFromRole(Long roleId, Long permissionId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("Role with id " + roleId));
        Permission permission = permissionRepository.findById(permissionId).orElseThrow(() -> new NotFoundException("Permission with id " + permissionId));
        role.getPermissions().remove(permission);
        roleRepository.save(role);
    }
}
