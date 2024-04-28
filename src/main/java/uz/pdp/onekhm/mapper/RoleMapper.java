package uz.pdp.onekhm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.onekhm.domain.Permission;
import uz.pdp.onekhm.domain.Role;
import uz.pdp.onekhm.dto.request.RoleDto;
import uz.pdp.onekhm.exception.NotFoundException;
import uz.pdp.onekhm.repo.PermissionRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleMapper {
    private final PermissionRepository permissionRepository;
    public RoleDto toDto(Role role) {
        List<Long> permissions = role.getPermissions().stream().map(Permission::getId).toList();
        return new RoleDto(
                role.getId(),
                role.getCode(),
                role.getDescription(),
                permissions
        );
    }
    public Role toEntity(RoleDto roleDto) {
        List<Permission> permissions = roleDto.getPermissions().stream().map(
                p->permissionRepository.findById(p).orElseThrow(
                        ()->new NotFoundException("Role with id " + p)
                )
        ).toList();
        return Role.builder()
                .id(roleDto.getId())
                .code(roleDto.getCode())
                .description(roleDto.getDescription())
                .permissions(permissions)
                .build();
    }
}
