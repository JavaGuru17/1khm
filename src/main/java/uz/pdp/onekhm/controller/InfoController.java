package uz.pdp.onekhm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.onekhm.dto.response.InfoDto;
import uz.pdp.onekhm.service.InfoService;
import uz.pdp.onekhm.utils.URL;

import java.util.Map;

@RestController
@RequestMapping(URL.HEAD_URL + URL.INFO_URL)
@RequiredArgsConstructor
public class InfoController {
    private final InfoService infoService;
    @PatchMapping(URL.UPDATE_URL)
    public ResponseEntity<?> update(@RequestBody InfoDto infoDto) {
        infoService.update(infoDto);
        return ResponseEntity.ok(Map.of("message","Info successfully updated"));
    }
    @GetMapping(URL.GET_URL + URL.INFO_URL)
    public ResponseEntity<?> getInfo(){
        return ResponseEntity.ok(infoService.getSiteInfo());
    }
}
