package uz.pdp.onekhm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.onekhm.utils.annotation.Length;

@AllArgsConstructor
@Getter
public class PermissionDto {
    Long id;
    @Length
    private String code;
    @Length
    private String description;
}
