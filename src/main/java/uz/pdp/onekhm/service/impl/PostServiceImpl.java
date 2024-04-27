package uz.pdp.onekhm.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.onekhm.domain.Post;
import uz.pdp.onekhm.dto.request.ChangeMediaDto;
import uz.pdp.onekhm.dto.request.PostSaveDto;
import uz.pdp.onekhm.dto.request.PostUpdateDto;
import uz.pdp.onekhm.exception.NotFoundException;
import uz.pdp.onekhm.repo.CategoryRepository;
import uz.pdp.onekhm.repo.PostRepository;
import uz.pdp.onekhm.service.PostService;
import uz.pdp.onekhm.utils.URL;
import uz.pdp.onekhm.utils.Validation;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @SneakyThrows
    public Post save(PostSaveDto postSaveDto, MultipartFile media){
        String id = UUID.randomUUID().toString();
        if (postSaveDto.getMediaPath() == null && media != null){
            Path path = Paths.get("src", "main", "resources", "static", "media");
            Files.createDirectories(path);
            path = path.resolve(id + media.getOriginalFilename());
            Files.copy(media.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
        }
        return postRepository.save(
                Post.builder()
                        .title(postSaveDto.getTitle())
                        .description(postSaveDto.getDescription())
                        .mediaPath("http://localhost:8080/api/v1/img?id=" + id)
                        .category(categoryRepository.findById(postSaveDto.getCategoryId()).orElseThrow(
                                ()->new NotFoundException("Category with id " + postSaveDto.getCategoryId())
                        ))
                        .build()
        );
    }

    @Override
    public Post update(PostUpdateDto postUpdateDto) {
        Post post = postRepository.findById(postUpdateDto.getId()).orElseThrow(
                () -> new NotFoundException("Post with id " + postUpdateDto.getId())
        );
        return postRepository.save(
                Post.builder()
                        .id(post.getId())
                        .title(Validation.requireNonNullElse(postUpdateDto.getTitle(),post.getTitle()))
                        .description(Validation.requireNonNullElse(postUpdateDto.getDescription(),post.getDescription()))
                        .category(postUpdateDto.getCategoryId() == null ? post.getCategory() : categoryRepository.findById(postUpdateDto.getCategoryId()).orElseThrow(
                                ()-> new NotFoundException("Category with id " + postUpdateDto.getCategoryId())
                        ))
                        .build()
        );
    }

    @Override
    public void delete(Long id) {
        if (!postRepository.existsById(id))
            throw new NotFoundException("Post with id " + id);
        postRepository.deleteById(id);
    }

    @Override
    public Post getById(Long id) {
        return postRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Post with id " + id)
        );
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getAllByCategoryId(Long id) {
        return postRepository.findAllByCategory(id);
    }

    @Override
    @SneakyThrows
    public void changeMedia(ChangeMediaDto mediaDto) {
        Post post = postRepository.findById(mediaDto.getId()).orElseThrow(
                () -> new NotFoundException("Post with id " + mediaDto.getId())
        );
        if (mediaDto.getMediaUrl() == null) {
            if (mediaDto.getMedia() != null){
                String id = UUID.randomUUID().toString();
                Path path = Paths.get("src", "main", "resources", "static", "media");
                Files.createDirectories(path);
                path = path.resolve(id + mediaDto.getMedia().getOriginalFilename());
                Files.copy(mediaDto.getMedia().getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
                post.setMediaPath(URL.BASE_URL + URL.HEAD_URL + URL.IMG_URL + "?=filename" + id);
            }else throw new NotFoundException("Media");
        }else {
            if (mediaDto.getMedia() == null){
                post.setMediaPath(mediaDto.getMediaUrl());
            }else throw new NotFoundException("Media");
        }
    }
}
