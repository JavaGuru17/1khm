package uz.pdp.onekhm.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.onekhm.domain.Category;

@AllArgsConstructor
@Getter
public class CategoryDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    public static CategoryDto toDto(Category category) {
        return new CategoryDto(category.getName(),category.getDescription());
    }
    public static Category toEntity(CategoryDto categoryDto){
        return Category.builder()
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .build();
    }
}
