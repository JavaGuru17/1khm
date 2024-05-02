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
import uz.pdp.onekhm.dto.request.RoleDto;
import uz.pdp.onekhm.dto.request.RoleUpdateDto;
import uz.pdp.onekhm.service.RoleService;
import uz.pdp.onekhm.utils.URL;

import java.util.Map;

@RestController
@RequestMapping(URL.HEAD_URL + URL.ROLE_URL)
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping(URL.SAVE_URL)
    public ResponseEntity<?> save(@RequestBody @Valid RoleDto roleDto) {
        return ResponseEntity.ok(roleService.save(roleDto));
    }

    @PatchMapping(URL.UPDATE_URL)
    public ResponseEntity<?> update(@RequestBody @Valid RoleUpdateDto roleDto) {
        return ResponseEntity.ok(roleService.update(roleDto));
    }

    @DeleteMapping(URL.DELETE_URL + URL.ID)
    public ResponseEntity<?> delete(@PathVariable @NotNull Long id) {
        roleService.delete(id);
        return ResponseEntity.ok(Map.of("message","Role successfully deleted"));
    }

    @GetMapping(URL.GET_URL + URL.ID)
    public ResponseEntity<?> get(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(roleService.getById(id));
    }

    @GetMapping(URL.GET_ALL_URL)
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @GetMapping(URL.GET_URL + URL.CODE_URL)
    public ResponseEntity<?> get(@PathVariable @NotBlank String code) {
        return ResponseEntity.ok(roleService.findByCode(code));
    }

    @PostMapping(URL.CHANGE_ROLE_URL)
    public ResponseEntity<?> changeRole(@PathVariable @NotNull Long roleId, @PathVariable @NotNull Long userId) {
        roleService.changeRole(roleId, userId);
        return ResponseEntity.ok(Map.of("message","Role successfully changed"));
    }
}
