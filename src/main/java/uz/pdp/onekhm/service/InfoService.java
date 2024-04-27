package uz.pdp.onekhm.service;

import org.springframework.stereotype.Service;
import uz.pdp.onekhm.dto.response.InfoDto;

@Service
public interface InfoService {
    void update(InfoDto infoDto);
    InfoDto getSiteInfo();
}
