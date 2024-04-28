package uz.pdp.onekhm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.onekhm.domain.Info;
import uz.pdp.onekhm.domain.PhoneNumber;
import uz.pdp.onekhm.dto.response.InfoDto;
import uz.pdp.onekhm.mapper.InfoMapper;
import uz.pdp.onekhm.repo.InfoRepository;
import uz.pdp.onekhm.repo.PhoneNumberRepo;
import uz.pdp.onekhm.service.InfoService;
import uz.pdp.onekhm.utils.Validation;

@Service
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService {
    private final InfoRepository infoRepository;
    private final PhoneNumberRepo phoneNumberRepo;
    private final InfoMapper infoMapper;

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
        return infoMapper.toDto(infoRepository.getInfo());
    }
}
