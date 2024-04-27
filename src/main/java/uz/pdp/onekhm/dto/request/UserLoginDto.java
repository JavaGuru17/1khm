package uz.pdp.onekhm.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserLoginDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
