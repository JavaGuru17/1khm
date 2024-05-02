package uz.pdp.onekhm.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.onekhm.domain.Permission;
import uz.pdp.onekhm.dto.request.PermissionDto;

@Component
public class PermissionMapper {
    public PermissionDto toDto(Permission permission) {
        return new PermissionDto(
                permission.getId(),
                permission.getCode().toUpperCase( ),
                permission.getDescription()
        );
    }

    public Permission toEntity(PermissionDto permissionDto) {
        return new Permission(
                permissionDto.getId(),
                permissionDto.getCode().toUpperCase(),
                permissionDto.getDescription()
        );
    }
}
