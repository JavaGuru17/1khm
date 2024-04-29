package uz.pdp.onekhm.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.onekhm.utils.annotation.Email;
import uz.pdp.onekhm.utils.annotation.Password;
import uz.pdp.onekhm.utils.annotation.PhoneNumber;

@AllArgsConstructor
@Getter
public class UserUpdateDto {
    @NotNull
    private Long id;
    private String name;
    private String surname;
    private String middleName;
    @Email
    private String email;
    @PhoneNumber
    private String phoneNumber;
    @Password
    private String password;
}
