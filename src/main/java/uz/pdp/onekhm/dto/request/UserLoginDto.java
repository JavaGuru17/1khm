package uz.pdp.onekhm.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.onekhm.utils.annotation.Email;
import uz.pdp.onekhm.utils.annotation.Password;

@AllArgsConstructor
@Getter
public class UserLoginDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Password
    private String password;
}
