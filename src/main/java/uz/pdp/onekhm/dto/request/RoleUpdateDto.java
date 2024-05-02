package uz.pdp.onekhm.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.onekhm.utils.annotation.Length;

@AllArgsConstructor
@Getter
public class RoleUpdateDto {
    @NotNull
    private Long id;
    @Length
    private String code;
    @Length
    private String description;
}
