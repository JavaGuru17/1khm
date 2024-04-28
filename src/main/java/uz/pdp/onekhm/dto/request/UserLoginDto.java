package uz.pdp.onekhm.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserLoginDto {
    @NotBlank
    /// todo email
    private String email;
    @NotBlank
    //todo password
    private String password;
}
