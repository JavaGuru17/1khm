package uz.pdp.onekhm.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostUpdateDto {
    @NotNull
    private Long id;
    private String title;
    private String description;
    private String mediaPath;
    private Long categoryId;
}
