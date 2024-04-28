package uz.pdp.onekhm.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.onekhm.utils.annotation.Length;
import uz.pdp.onekhm.utils.annotation.Link;

@AllArgsConstructor
@Getter
public class PostSaveDto {
    @NotBlank
    @Length
    private String title;
    @NotBlank
    @Length
    private String description;
    @Link
    private String mediaPath;
    @NotNull
    private Long categoryId;
}
