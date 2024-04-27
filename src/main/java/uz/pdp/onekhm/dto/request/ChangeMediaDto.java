package uz.pdp.onekhm.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Getter
public class ChangeMediaDto {
    @NotNull
    private Long id;
    private MultipartFile media;
    private String mediaUrl;
}
