package uz.pdp.onekhm.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.onekhm.utils.annotation.Length;
import uz.pdp.onekhm.utils.annotation.Link;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InfoDto {
    @NotBlank
    @Length
    private String title;
    @NotBlank
    @Length
    private String description;
    @NotBlank
    @Link
    private String logoUrl;
    @NotBlank
    //todo behzod please add email annotation
    private String email;
    @NotEmpty
    //todo phonenumbers annotatiion
    private List<String> phoneNumbers;
}
