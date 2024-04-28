package uz.pdp.onekhm.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.onekhm.utils.annotation.Length;

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
    //todo email
    private String email;
    @NotBlank
    //todo phone number
    private String phoneNumber;
    @NotBlank
    //todo password
    private String password;
    @NotBlank
    private String confirmPassword;
}
