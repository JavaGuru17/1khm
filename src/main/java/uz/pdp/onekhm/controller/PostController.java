package uz.pdp.onekhm.controller;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.onekhm.dto.request.PostSaveDto;
import uz.pdp.onekhm.dto.request.PostUpdateDto;
import uz.pdp.onekhm.service.PostService;
import uz.pdp.onekhm.utils.URL;

import java.util.Map;



@RestController
@MultipartConfig
@RequestMapping(value = URL.HEAD_URL + URL.POST_URL)
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping(value =URL.SAVE_URL,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> save(@RequestPart("file") @Valid MultipartFile file,@RequestPart("postSaveDto") @Valid PostSaveDto postSaveDto) {
        return ResponseEntity.ok(postService.save(postSaveDto, file));
    }
    @PatchMapping(URL.UPDATE_URL)
    public ResponseEntity<?> update(@RequestBody @Valid PostUpdateDto postUpdateDto) {
        return ResponseEntity.ok(postService.update(postUpdateDto));
    }
    @DeleteMapping(URL.DELETE_URL + URL.ID)
    public ResponseEntity<?> delete(@PathVariable @NotNull Long id) {
        postService.delete(id);
        return ResponseEntity.ok(Map.of("message","Product successfully deleted"));
    }
    @GetMapping(URL.GET_URL + URL.ID)
    public ResponseEntity<?> getById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(postService.getById(id));
    }
    @GetMapping(URL.GET_ALL_URL)
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(postService.getAll());
    }
    @GetMapping(URL.GET_URL + URL.CATEGORY_URL + URL.ID)
    public ResponseEntity<?> getByCategoryId(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(postService.getAllByCategoryId(id));
    }
}