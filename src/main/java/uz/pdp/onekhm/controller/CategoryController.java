package uz.pdp.onekhm.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.onekhm.dto.request.CategoryDto;
import uz.pdp.onekhm.service.CategoryService;
import uz.pdp.onekhm.utils.URL;

import java.util.Map;

@RestController
@RequestMapping(URL.HEAD_URL + URL.CATEGORY_URL)
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping(URL.SAVE_URL)
    public ResponseEntity<?> save(@RequestBody @Valid CategoryDto category) {
        return ResponseEntity.ok(categoryService.save(category));
    }
    @DeleteMapping(URL.DELETE_URL + URL.ID)
    public ResponseEntity<?> delete(@PathVariable @NotNull Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok(Map.of("message","category successfully deleted"));
    }
    @GetMapping(URL.ID)
    public ResponseEntity<?> getById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }
    @GetMapping(URL.GET_ALL_URL)
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(categoryService.getAll());
    }
    @GetMapping(URL.GET_URL + URL.NAME)
    public ResponseEntity<?> getByName(@PathVariable @NotBlank String name) {
        return ResponseEntity.ok(categoryService.getByName(name));
    }
}