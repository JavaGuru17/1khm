package uz.pdp.onekhm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.onekhm.domain.User;
import uz.pdp.onekhm.dto.request.UserLoginDto;
import uz.pdp.onekhm.dto.request.UserRegisterDto;
import uz.pdp.onekhm.dto.response.UserDto;
import uz.pdp.onekhm.exception.AlreadyExistsException;
import uz.pdp.onekhm.exception.InvalidArgumentException;
import uz.pdp.onekhm.exception.NotFoundException;
import uz.pdp.onekhm.repo.UserRepository;
import uz.pdp.onekhm.service.UserService;
import uz.pdp.onekhm.utils.Validation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserRegisterDto register(UserRegisterDto userRegisterDto) {
        if (userRepository.findByEmail(userRegisterDto.getEmail()).isPresent())
            throw new AlreadyExistsException("User with this email already exists");

        User user = userRepository.save(User.builder()
                .name(userRegisterDto.getName())
                .surname(userRegisterDto.getSurname())
                .middleName(userRegisterDto.getMiddleName())
                .email(userRegisterDto.getEmail())
                .phoneNumber(userRegisterDto.getPhoneNumber())
                .password(userRegisterDto.getPassword())
                .build());
        return new UserRegisterDto(user);
    }

    @Override
    public void login(UserLoginDto userLoginDto) {
        User user = userRepository.findByEmail(userLoginDto.getEmail()).orElseThrow(
                () -> new NotFoundException("User with email " + userLoginDto.getEmail()));
        if (!user.getPassword().equals(userLoginDto.getPassword()))
            throw new InvalidArgumentException("User with password " + userLoginDto.getPassword());
    }

    @Override
    public User update(User user) {
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException("User"));

        User updatedUser = User.builder()
                .id(existingUser.getId())
                .name(Validation.requireNonNullElse(user.getName(), existingUser.getName()))
                .surname(Validation.requireNonNullElse(user.getSurname(), existingUser.getSurname()))
                .middleName(Validation.requireNonNullElse(user.getMiddleName(), existingUser.getMiddleName()))
                .email(Validation.requireNonNullElse(user.getEmail(), existingUser.getEmail()))
                .phoneNumber(Validation.requireNonNullElse(user.getPhoneNumber(), existingUser.getPhoneNumber()))
                .password(Validation.requireNonNullElse(user.getPassword(), existingUser.getPassword()))
                .role(Validation.requireNonNullElse(user.getRole(), existingUser.getRole()))
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
        return new UserDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(UserDto::new).toList();
    }

    @Override
    public List<UserDto> getAllByRole(String code) {
        return userRepository.findAllByRoleCode(code).stream().map(UserDto::new).toList();
    }

    @Override
    public UserDto getByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email " + email));
        return new UserDto(user);
    }

    @Override
    public UserDto getByPhoneNumber(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new NotFoundException("User with phone number " + phoneNumber));
        return new UserDto(user);
    }
}
