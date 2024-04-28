package uz.pdp.onekhm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.onekhm.domain.User;
import uz.pdp.onekhm.dto.request.UserRegisterDto;
import uz.pdp.onekhm.dto.response.UserDto;
import uz.pdp.onekhm.exception.NotFoundException;
import uz.pdp.onekhm.repo.RoleRepository;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getMiddleName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getRole());
    }
    public User toEntity(UserRegisterDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .middleName(userDto.getMiddleName())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(roleRepository.findByCode("ROLE_USER").orElseThrow(()-> new NotFoundException("Role with name USER")))
                .build();
    }
}
