package uz.pdp.onekhm.dto.request;

import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
public class RoleDto {
    private Long id;
    @NotBlank
    private String code;
    @NotBlank
    private String description;
    @ManyToMany
    @ToString.Exclude
    private List<Long> permissions;
}
