package uz.pdp.onekhm.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.onekhm.domain.User;

@AllArgsConstructor
@Getter
public class UserRegisterDto {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String middleName;
    @NotBlank
    private String email;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;

    public UserRegisterDto(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.middleName = user.getMiddleName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.password = user.getPassword();
    }
}
