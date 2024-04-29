package uz.pdp.onekhm.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.onekhm.utils.annotation.Link;

@AllArgsConstructor
@Getter
public class ChangeMediaDto {
    @NotNull
    private Long id;
    @Link
    private String mediaUrl;
}
