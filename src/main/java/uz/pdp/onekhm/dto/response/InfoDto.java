package uz.pdp.onekhm.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
}
