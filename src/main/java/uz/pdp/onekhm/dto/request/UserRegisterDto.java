package uz.pdp.onekhm.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.onekhm.utils.annotation.Email;
import uz.pdp.onekhm.utils.annotation.Length;
import uz.pdp.onekhm.utils.annotation.Password;
import uz.pdp.onekhm.utils.annotation.PhoneNumber;

@AllArgsConstructor
@Getter
public class UserRegisterDto {
    @NotBlank
    @Length
    private String name;
    @NotBlank
    @Length
    private String surname;
    private String middleName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @PhoneNumber
    private String phoneNumber;
    @NotBlank
    @Password
    private String password;
    @NotBlank
    private String confirmPassword;
}
