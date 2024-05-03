package uz.pdp.onekhm.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.onekhm.dto.request.InfoUpdateDto;
import uz.pdp.onekhm.dto.response.InfoDto;

@Service
public interface InfoService {
    void update(MultipartFile file, InfoUpdateDto infoDto);
    InfoDto getSiteInfo();
}
