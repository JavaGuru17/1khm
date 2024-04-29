package uz.pdp.onekhm.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.onekhm.dto.request.UserUpdateDto;
import uz.pdp.onekhm.service.UserService;
import uz.pdp.onekhm.utils.URL;

import java.util.Map;

@RestController
@RequestMapping(URL.HEAD_URL + URL.USER_URL)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PatchMapping(URL.UPDATE_URL)
    public ResponseEntity<?> update(@RequestBody @Valid UserUpdateDto user) {
        return ResponseEntity.ok(userService.update(user));
    }

    @DeleteMapping(URL.DELETE_URL + URL.ID)
    public ResponseEntity<?> delete(@PathVariable @NotNull Long id) {
        userService.delete(id);
        return ResponseEntity.ok(Map.of("message","User successfully deleted"));
    }

    @GetMapping(URL.GET_URL + URL.ID)
    public ResponseEntity<?> getById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping(URL.GET_ALL_URL)
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping(URL.GET_ALL_URL + URL.CODE_URL)
    public ResponseEntity<?> getAllByRole(@PathVariable @NotNull String code) {
        return ResponseEntity.ok(userService.getAllByRole(code));
    }

    @GetMapping(URL.GET_URL + URL.EMAIL_URL)
    public ResponseEntity<?> getByEmail(@PathVariable @NotNull String email) {
        return ResponseEntity.ok(userService.getByEmail(email));
    }

    @GetMapping(URL.GET_URL + URL.PHONE_NUMBER_URL)
    public ResponseEntity<?> getByPhoneNumber(@PathVariable @NotNull String phoneNumber) {
        return ResponseEntity.ok(userService.getByPhoneNumber(phoneNumber));
    }
}
