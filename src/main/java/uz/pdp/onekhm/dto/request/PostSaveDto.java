package uz.pdp.onekhm.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostSaveDto {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private String mediaPath;
    @NotNull
    private Long categoryId;
}
