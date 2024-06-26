package uz.pdp.onekhm.service;

import org.springframework.stereotype.Service;
import uz.pdp.onekhm.dto.request.RoleDto;
import uz.pdp.onekhm.dto.request.RoleUpdateDto;

import java.util.List;

@Service
public interface RoleService {
    RoleDto save(RoleDto roleDto);
    RoleDto update(RoleUpdateDto roleDto);
    void delete(Long id);
    RoleDto getById(Long id);
    List<RoleDto> getAll();
    RoleDto findByCode(String code);
    void changeRole(Long roleId, Long userId);
}
