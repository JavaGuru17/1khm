package uz.pdp.onekhm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.onekhm.domain.Post;
import uz.pdp.onekhm.dto.request.PostSaveDto;
import uz.pdp.onekhm.exception.NotFoundException;
import uz.pdp.onekhm.repo.CategoryRepository;

@Component
@RequiredArgsConstructor
public class PostMapper {

    private final CategoryRepository categoryRepository;

    public Post toEntity(PostSaveDto postSaveDto,String mediaPath){
        return Post.builder()
                .title(postSaveDto.getTitle())
                .description(postSaveDto.getDescription())
                .mediaPath(mediaPath)
                .category(categoryRepository.findById(postSaveDto.getCategoryId()).orElseThrow(
                        ()->new NotFoundException("Category with id " + postSaveDto.getCategoryId())
                ))
                .build();
    }
}
