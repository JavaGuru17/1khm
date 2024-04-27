package uz.pdp.onekhm.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.onekhm.domain.Permission;
import uz.pdp.onekhm.service.PermissionService;
import uz.pdp.onekhm.utils.URL;

import java.util.List;

@RestController
@RequestMapping(URL.HEAD_URL + URL.PERMISSION_URL)
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @PostMapping(URL.SAVE_URL)
    public Permission save(@RequestBody @Valid Permission permission) {
        return permissionService.save(permission);
    }

    @PatchMapping(URL.UPDATE_URL)
    public Permission update(@RequestBody @Valid Permission permission) {
        return permissionService.update(permission);
    }

    @DeleteMapping(URL.DELETE_URL + URL.ID)
    public ResponseEntity<?> delete(@PathVariable @NotNull Long id) {
        permissionService.delete(id);
        return ResponseEntity.ok("Successfully deleted");
    }

    @GetMapping(URL.GET_URL + URL.ID)
    public Permission get(@PathVariable @NotNull Long id) {
        return permissionService.getById(id);
    }

    @GetMapping(URL.GET_ALL_URL)
    public List<Permission> getAll() {
        return permissionService.getAll();
    }

    @GetMapping(URL.GET_URL + URL.CODE_URL)
    public Permission getCode(@PathVariable @NotBlank String code) {
        return permissionService.findByCode(code);
    }
}
