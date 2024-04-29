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

import java.util.Map;

@RestController
@RequestMapping(URL.HEAD_URL + URL.PERMISSION_URL)
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @PostMapping(URL.SAVE_URL)
    public ResponseEntity<?> save(@RequestBody @Valid Permission permission) {
        return ResponseEntity.ok(permissionService.save(permission));
    }

    @PatchMapping(URL.UPDATE_URL)
    public ResponseEntity<?> update(@RequestBody @Valid Permission permission) {
        return ResponseEntity.ok(permissionService.update(permission));
    }

    @DeleteMapping(URL.DELETE_URL + URL.ID)
    public ResponseEntity<?> delete(@PathVariable @NotNull Long id) {
        permissionService.delete(id);
        return ResponseEntity.ok(Map.of("message", "Permission successfully deleted"));
    }

    @GetMapping(URL.GET_URL + URL.ID)
    public ResponseEntity<?> get(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(permissionService.getById(id));
    }

    @GetMapping(URL.GET_ALL_URL)
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(permissionService.getAll());
    }

    @GetMapping(URL.GET_URL + URL.CODE_URL)
    public ResponseEntity<?> getCode(@PathVariable @NotBlank String code) {
        return ResponseEntity.ok(permissionService.findByCode(code));
    }

    @PostMapping(URL.ADD_PERMISSION_URL)
    public ResponseEntity<?> addPermission(@PathVariable @NotNull Long permissionId, @PathVariable @NotNull Long roleId) {
        permissionService.addToRole(roleId, permissionId);
        return ResponseEntity.ok(Map.of("message", "Permission successfully added"));
    }

    @PostMapping(URL.REMOVE_PERMISSION_URL)
    public ResponseEntity<?> removePermission(@PathVariable @NotNull Long permissionId, @PathVariable @NotNull Long roleId) {
        permissionService.removeFromRole(roleId, permissionId);
        return ResponseEntity.ok(Map.of("message", "Permission successfully removed"));
    }
}
