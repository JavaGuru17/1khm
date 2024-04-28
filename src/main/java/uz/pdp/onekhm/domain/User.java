package uz.pdp.onekhm.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import uz.pdp.onekhm.utils.annotation.Length;
import uz.pdp.onekhm.utils.annotation.PhoneNumber;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Length
    private String name;
    @NotBlank
    @Length
    private String surname;
    @Length
    private String middleName;
    @NotBlank
    //todo email
    private String email;
    @NotBlank
    @PhoneNumber
    private String phoneNumber;
    @NotBlank
    //todo password
    private String password;
    @ManyToOne
    private Role role;
}
