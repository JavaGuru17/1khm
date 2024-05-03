package uz.pdp.onekhm.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.onekhm.dto.request.InfoUpdateDto;
import uz.pdp.onekhm.service.InfoService;
import uz.pdp.onekhm.utils.URL;

import java.util.Map;

@RestController
@RequestMapping(URL.HEAD_URL + URL.INFO_URL)
@RequiredArgsConstructor
public class InfoController {
    private final InfoService infoService;
    @PatchMapping(value = URL.UPDATE_URL,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> update(@RequestPart(value = "file",required = false)MultipartFile file, @RequestPart("infoDto") @Valid InfoUpdateDto infoDto) {
        infoService.update(file,infoDto);
        return ResponseEntity.ok(Map.of("message","Info successfully updated"));
    }
    @GetMapping(URL.GET_URL + URL.INFO_URL)
    public ResponseEntity<?> getInfo(){
        return ResponseEntity.ok(infoService.getSiteInfo());
    }
}
