package uz.pdp.onekhm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.onekhm.dto.request.PostSaveDto;
import uz.pdp.onekhm.dto.request.PostUpdateDto;
import uz.pdp.onekhm.service.PostService;
import uz.pdp.onekhm.utils.URL;

import java.util.Map;



@RestController
@RequestMapping(value = URL.HEAD_URL + URL.POST_URL)
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping(value =URL.SAVE_URL)
    public ResponseEntity<?> save(MultipartFile file, @RequestBody PostSaveDto postSaveDto) {
        return ResponseEntity.ok(postService.save(postSaveDto, file));
    }
    @PatchMapping(URL.UPDATE_URL)
    public ResponseEntity<?> update(@RequestBody PostUpdateDto postUpdateDto) {
        return ResponseEntity.ok(postService.update(postUpdateDto));
    }
    @DeleteMapping(URL.DELETE_URL + URL.ID_URL)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.ok(Map.of("message","Product successfully deleted"));
    }
    @GetMapping(URL.GET_URL + URL.ID_URL)
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getById(id));
    }
    @GetMapping(URL.GET_ALL_URL)
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(postService.getAll());
    }
    @GetMapping(URL.GET_URL + URL.CATEGORY_URL + URL.ID_URL)
    public ResponseEntity<?> getByCategoryId(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getAllByCategoryId(id));
    }
}