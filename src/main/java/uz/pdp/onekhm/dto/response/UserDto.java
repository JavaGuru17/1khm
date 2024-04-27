package uz.pdp.onekhm.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.onekhm.domain.Role;
import uz.pdp.onekhm.domain.User;

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

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.middleName = user.getMiddleName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.role = user.getRole();
    }
}
