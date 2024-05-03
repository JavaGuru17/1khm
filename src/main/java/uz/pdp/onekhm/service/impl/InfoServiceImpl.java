package uz.pdp.onekhm.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.onekhm.domain.Info;
import uz.pdp.onekhm.domain.PhoneNumber;
import uz.pdp.onekhm.dto.request.InfoUpdateDto;
import uz.pdp.onekhm.dto.response.InfoDto;
import uz.pdp.onekhm.mapper.InfoMapper;
import uz.pdp.onekhm.repo.InfoRepository;
import uz.pdp.onekhm.repo.PhoneNumberRepo;
import uz.pdp.onekhm.service.InfoService;
import uz.pdp.onekhm.utils.URL;
import uz.pdp.onekhm.utils.Validation;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService {
    private final InfoRepository infoRepository;
    private final PhoneNumberRepo phoneNumberRepo;
    private final InfoMapper infoMapper;

    @Override
    @SneakyThrows
    public void update(MultipartFile file, InfoUpdateDto infoDto) {
        Info info = infoRepository.getInfo();
        String logoUrl = infoDto.getLogoUrl();
        if (file != null) {
            String id = UUID.randomUUID().toString();
            Path path = Paths.get("src", "main", "resources", "static", "media");
            path = path.resolve(id + file.getOriginalFilename());
            Files.createDirectories(path);
            Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
            logoUrl = URL.BASE_URL + URL.HEAD_URL + URL.IMG_URL + "/" + id + file.getOriginalFilename();
        }
        infoRepository.save(
                Info.builder()
                        .id(1L)
                        .title(Validation.requireNonNullElse(infoDto.getTitle(),info.getTitle()))
                        .description(Validation.requireNonNullElse(infoDto.getDescription(),info.getDescription()))
                        .logoUrl(Validation.requireNonNullElse(logoUrl,info.getLogoUrl()))
                        .email(Validation.requireNonNullElse(infoDto.getEmail(),info.getEmail()))
                        .build()
        );
        if (infoDto.getPhoneNumbers() != null && !infoDto.getPhoneNumbers().isEmpty()){
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
