package uz.pdp.onekhm.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.onekhm.domain.Info;


import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class InfoDto {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String logoUrl;
    @NotBlank
    private String email;
    @NotEmpty
    private List<String> phoneNumbers;
    public InfoDto(Info info){
        this.title = info.getTitle();
        this.description = info.getDescription();
        this.logoUrl = info.getLogoUrl();
        this.email = info.getEmail();
        this.phoneNumbers = new ArrayList<>();
    }
    public static Info toEntity(InfoDto infoDto){
        return Info.builder()
                .title(infoDto.getTitle())
                .description(infoDto.getDescription())
                .logoUrl(infoDto.getLogoUrl())
                .email(infoDto.getEmail())
                .build();
    }
}
