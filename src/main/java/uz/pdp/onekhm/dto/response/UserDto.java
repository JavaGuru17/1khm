package uz.pdp.onekhm.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.onekhm.domain.Role;

@AllArgsConstructor
@Getter
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String middleName;
    private String email;
    private String phoneNumber;
    private Role role;
}
