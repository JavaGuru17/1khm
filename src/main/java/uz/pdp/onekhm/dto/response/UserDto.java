package uz.pdp.onekhm.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.onekhm.domain.Role;
import uz.pdp.onekhm.utils.annotation.Email;
import uz.pdp.onekhm.utils.annotation.Length;
import uz.pdp.onekhm.utils.annotation.PhoneNumber;

@AllArgsConstructor
@Getter
public class UserDto {
    private Long id;
    @Length
    private String name;
    @Length
    private String surname;
    @Length
    private String middleName;
    @Email
    private String email;
    @PhoneNumber
    private String phoneNumber;
    private Role role;
}
