package uz.pdp.onekhm.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.onekhm.dto.request.UserLoginDto;
import uz.pdp.onekhm.dto.request.UserRegisterDto;
import uz.pdp.onekhm.service.UserService;
import uz.pdp.onekhm.utils.URL;

@RestController
@RequestMapping(URL.HEAD_URL + URL.AUTH_URL)
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping(URL.REGISTER_URL)
    public ResponseEntity<?> register(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        return ResponseEntity.ok(userService.register(userRegisterDto));
    }

    @PostMapping(URL.LOGIN_URL)
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginDto userLoginDto) {
        userService.login(userLoginDto);
        return ResponseEntity.ok("login success");
    }
}
