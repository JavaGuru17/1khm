package uz.pdp.onekhm.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.onekhm.utils.annotation.Length;
import uz.pdp.onekhm.utils.annotation.Link;

@AllArgsConstructor
@Getter
public class PostUpdateDto {
    @NotNull
    private Long id;
    @Length
    private String title;
    @Length
    private String description;
    @Link
    private String mediaPath;
    private Long categoryId;
}
