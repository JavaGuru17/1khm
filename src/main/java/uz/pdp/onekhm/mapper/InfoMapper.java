package uz.pdp.onekhm.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.onekhm.domain.Info;
import uz.pdp.onekhm.dto.response.InfoDto;
import uz.pdp.onekhm.repo.PhoneNumberRepo;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InfoMapper {
    private final PhoneNumberRepo phoneNumberRepo;

    public InfoDto toDto(Info info){
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumberRepo.findAll().forEach(p -> phoneNumbers.add(p.getPhone()));
        InfoDto infoDto = new InfoDto();
        infoDto.setTitle(info.getTitle());
        infoDto.setDescription(info.getDescription());
        infoDto.setLogoUrl(info.getLogoUrl());
        infoDto.setEmail(info.getEmail());
        infoDto.setPhoneNumbers(phoneNumbers);
        return infoDto;
    }
}
