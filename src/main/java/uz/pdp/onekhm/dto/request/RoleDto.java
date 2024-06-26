package uz.pdp.onekhm.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.onekhm.utils.annotation.Length;

import java.util.List;

@AllArgsConstructor
@Getter
public class RoleDto {
    private Long id;
    @NotBlank
    @Length
    private String code;
    @NotBlank
    @Length
    private String description;
    private List<Long> permissions;
}
