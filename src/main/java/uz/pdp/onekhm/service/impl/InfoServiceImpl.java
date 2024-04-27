package uz.pdp.onekhm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.onekhm.domain.Info;
import uz.pdp.onekhm.domain.PhoneNumber;
import uz.pdp.onekhm.dto.response.InfoDto;
import uz.pdp.onekhm.repo.InfoRepository;
import uz.pdp.onekhm.repo.PhoneNumberRepo;
import uz.pdp.onekhm.service.InfoService;
import uz.pdp.onekhm.utils.Validation;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService {
    private final InfoRepository infoRepository;
    private final PhoneNumberRepo phoneNumberRepo;

    @Override
    public void update(InfoDto infoDto) {
        Info info = infoRepository.getInfo();
        infoRepository.save(
                Info.builder()
                        .id(1L)
                        .title(Validation.requireNonNullElse(infoDto.getTitle(),info.getTitle()))
                        .description(Validation.requireNonNullElse(infoDto.getDescription(),info.getDescription()))
                        .logoUrl(Validation.requireNonNullElse(infoDto.getLogoUrl(),info.getLogoUrl()))
                        .email(Validation.requireNonNullElse(infoDto.getEmail(),info.getEmail()))
                        .build()
        );
        if (!infoDto.getPhoneNumbers().isEmpty()){
            infoDto.getPhoneNumbers().forEach(p->{
                if (!phoneNumberRepo.existsById(p))
                    phoneNumberRepo.save(new PhoneNumber(p));
            });
        }
    }

    @Override
    public InfoDto getSiteInfo() {
        Info info = infoRepository.getInfo();
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumberRepo.findAll().forEach(p -> phoneNumbers.add(p.getPhone()));
        return new InfoDto(
                info.getTitle(),
                info.getDescription(),
                info.getLogoUrl(),
                info.getEmail(),
                phoneNumbers
        );
    }
}
