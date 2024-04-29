package uz.pdp.onekhm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.onekhm.domain.Role;
import uz.pdp.onekhm.domain.User;
import uz.pdp.onekhm.dto.request.RoleDto;
import uz.pdp.onekhm.exception.AlreadyExistsException;
import uz.pdp.onekhm.exception.NotFoundException;
import uz.pdp.onekhm.mapper.RoleMapper;
import uz.pdp.onekhm.repo.RoleRepository;
import uz.pdp.onekhm.repo.UserRepository;
import uz.pdp.onekhm.service.RoleService;
import uz.pdp.onekhm.utils.Validation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final UserRepository userRepository;

    @Override
    public RoleDto save(RoleDto roleDto) {
        if (roleRepository.existsById(roleDto.getId()))
            throw new AlreadyExistsException("Role with id " + roleDto.getId());
        if (roleRepository.findByCode(roleDto.getCode()).isPresent())
            throw new AlreadyExistsException("Role with code " + roleDto.getCode());
        return roleMapper.toDto(roleRepository.save(roleMapper.toEntity(roleDto)));
    }

    @Override
    public RoleDto update(RoleDto roleDto) {
        Role existingRole = roleRepository.findById(roleDto.getId())
                .orElseThrow(() -> new NotFoundException("Role with id " + roleDto.getId()));
        Role updatedRole = Role.builder()
                .id(existingRole.getId())
                .code(Validation.requireNonNullElse(roleDto.getCode(), existingRole.getCode()))
                .description(Validation.requireNonNullElse(roleDto.getDescription(), existingRole.getDescription()))
                .build();

        Role role = roleRepository.save(updatedRole);
        return roleMapper.toDto(role);
    }

    @Override
    public void delete(Long id) {
        if (!roleRepository.existsById(id))
            throw new NotFoundException("Role with id " + id);
        roleRepository.deleteById(id);
    }

    @Override
    public RoleDto getById(Long id) {
        if (!roleRepository.existsById(id))
            throw new NotFoundException("Role with id " + id);
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Role with id " + id));
        return roleMapper.toDto(role);
    }

    @Override
    public List<RoleDto> getAll() {
        return roleRepository.findAll().stream().map(roleMapper::toDto).toList();
    }

    @Override
    public RoleDto findByCode(String code) {
        Role role = roleRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Role with code " + code));
        return roleMapper.toDto(role);
    }

    @Override
    public void changeRole(Long roleId, Long userId){
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("Role with id " + roleId));
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with id " + userId));
        user.setRole(role);
        userRepository.save(user);
    }
}
