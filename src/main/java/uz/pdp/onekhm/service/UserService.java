package uz.pdp.onekhm.service;

import org.springframework.stereotype.Service;
import uz.pdp.onekhm.domain.User;
import uz.pdp.onekhm.dto.request.UserLoginDto;
import uz.pdp.onekhm.dto.request.UserRegisterDto;
import uz.pdp.onekhm.dto.request.UserUpdateDto;
import uz.pdp.onekhm.dto.response.JwtDto;
import uz.pdp.onekhm.dto.response.UserDto;

import java.util.List;

@Service
public interface UserService {
    JwtDto register(UserRegisterDto userRegisterDto);
    JwtDto login(UserLoginDto userLoginDto);
    User update(UserUpdateDto userUpdateDto);
    void delete(Long id);
    UserDto getById(Long id);
    List<UserDto> getAll();
    List<UserDto> getAllByRole(String code);
    UserDto getByEmail(String email);
    UserDto getByPhoneNumber(String phoneNumber);
}
