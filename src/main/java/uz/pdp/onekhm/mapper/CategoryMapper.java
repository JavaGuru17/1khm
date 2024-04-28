package uz.pdp.onekhm.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.onekhm.domain.Category;
import uz.pdp.onekhm.dto.request.CategoryDto;

@Component
public class CategoryMapper {
    public Category toEntity(CategoryDto categoryDto){
        return Category.builder()
                .name(categoryDto.getName().toUpperCase())
                .description(categoryDto.getDescription())
                .build();
    }
}
