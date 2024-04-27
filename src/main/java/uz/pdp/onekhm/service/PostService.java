package uz.pdp.onekhm.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.onekhm.domain.Post;
import uz.pdp.onekhm.dto.request.ChangeMediaDto;
import uz.pdp.onekhm.dto.request.PostSaveDto;
import uz.pdp.onekhm.dto.request.PostUpdateDto;

import java.util.List;

@Service
public interface PostService {
    Post save(PostSaveDto postSaveDto, MultipartFile media);
    Post update(PostUpdateDto postUpdateDto);
    void delete(Long id);
    Post getById(Long id);
    List<Post> getAll();
    List<Post> getAllByCategoryId(Long id);
    void changeMedia(ChangeMediaDto mediaDto);
}
