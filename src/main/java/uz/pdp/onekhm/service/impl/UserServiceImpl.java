package uz.pdp.onekhm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.onekhm.domain.User;
import uz.pdp.onekhm.dto.request.UserLoginDto;
import uz.pdp.onekhm.dto.request.UserRegisterDto;
import uz.pdp.onekhm.dto.request.UserUpdateDto;
import uz.pdp.onekhm.dto.response.JwtDto;
import uz.pdp.onekhm.dto.response.UserDto;
import uz.pdp.onekhm.exception.AlreadyExistsException;
import uz.pdp.onekhm.exception.InvalidArgumentException;
import uz.pdp.onekhm.exception.NotFoundException;
import uz.pdp.onekhm.mapper.UserMapper;
import uz.pdp.onekhm.repo.UserRepository;
import uz.pdp.onekhm.security.jwt.JwtProvider;
import uz.pdp.onekhm.service.UserService;
import uz.pdp.onekhm.utils.Validation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public JwtDto register(UserRegisterDto userRegisterDto) {
        if (userRepository.findByEmail(userRegisterDto.getEmail()).isPresent())
            throw new AlreadyExistsException("User with email " + userRegisterDto.getEmail());

        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            throw new InvalidArgumentException("Passwords do not match");
        }
        User user = userRepository.save(userMapper.toEntity(userRegisterDto));
        return new JwtDto(jwtProvider.generateToken(user));
    }

    @Override
    public JwtDto login(UserLoginDto userLoginDto) {
        User user = userRepository.findByEmail(userLoginDto.getEmail()).orElseThrow(
                () -> new NotFoundException("User with email " + userLoginDto.getEmail()));
        if (!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword()))
            throw new InvalidArgumentException("User with password " + userLoginDto.getPassword());
        return new JwtDto(jwtProvider.generateToken(user));
    }

    @Override
    public User update(UserUpdateDto userUpdateDto) {
        User existingUser = userRepository.findById(userUpdateDto.getId())
                .orElseThrow(() -> new NotFoundException("User"));

        if (userUpdateDto.getEmail() != null)
            userRepository.findByEmail(userUpdateDto.getEmail()).ifPresent((e) -> {
                throw new AlreadyExistsException("User with email " + e.getEmail());
            });
        if (userUpdateDto.getPhoneNumber() != null)
            userRepository.findByPhoneNumber(userUpdateDto.getPhoneNumber()).ifPresent((e) -> {
                throw new AlreadyExistsException("User with phone number " + e.getPhoneNumber());
            });

        User updatedUser = User.builder()
                .id(existingUser.getId())
                .name(Validation.requireNonNullElse(userUpdateDto.getName(), existingUser.getName()))
                .surname(Validation.requireNonNullElse(userUpdateDto.getSurname(), existingUser.getSurname()))
                .email(Validation.requireNonNullElse(userUpdateDto.getEmail(), existingUser.getEmail()))
                .middleName(Validation.requireNonNullElse(userUpdateDto.getMiddleName(), existingUser.getMiddleName()))
                .phoneNumber(Validation.requireNonNullElse(userUpdateDto.getPhoneNumber(), existingUser.getPhoneNumber()))
                .password(userUpdateDto.getPassword() != null ? passwordEncoder.encode(userUpdateDto.getPassword()) : existingUser.getPassword())
                .role(existingUser.getRole())
                .build();

        return userRepository.save(updatedUser);
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id))
            throw new NotFoundException("User with id " + id);
        userRepository.deleteById(id);
    }

    @Override
    public UserDto getById(Long id) {
        if (!userRepository.existsById(id))
            throw new NotFoundException("User with id " + id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    @Override
    public List<UserDto> getAllByRole(String code) {
        return userRepository.findAllByRoleCode(code).stream().map(userMapper::toDto).toList();
    }

    @Override
    public UserDto getByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email " + email));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto getByPhoneNumber(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new NotFoundException("User with phone number " + phoneNumber));
        return userMapper.toDto(user);
    }
}
