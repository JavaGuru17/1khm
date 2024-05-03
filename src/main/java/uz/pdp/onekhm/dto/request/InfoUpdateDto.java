package uz.pdp.onekhm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.pdp.onekhm.utils.annotation.Email;
import uz.pdp.onekhm.utils.annotation.Length;
import uz.pdp.onekhm.utils.annotation.Link;
import uz.pdp.onekhm.utils.annotation.PhoneNumber;

import java.util.List;

@AllArgsConstructor
@Getter
public class InfoUpdateDto {
    @Length
    private String title;
    @Length
    private String description;
    @Link
    private String logoUrl;
    @Email
    private String email;
    @PhoneNumber
    private List<String> phoneNumbers;
}
