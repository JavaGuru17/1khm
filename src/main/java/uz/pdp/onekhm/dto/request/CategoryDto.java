package uz.pdp.onekhm.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.onekhm.utils.annotation.Length;

@AllArgsConstructor
@Getter
public class CategoryDto {
    @NotBlank
    @Length
    private String name;
    @NotBlank
    @Length
    private String description;
}
