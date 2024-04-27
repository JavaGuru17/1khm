package uz.pdp.onekhm.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.onekhm.exception.NotFoundException;
import uz.pdp.onekhm.utils.URL;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping(URL.HEAD_URL + URL.IMG_URL)
public class ImgController {
    @GetMapping("{filename}")
    public ResponseEntity<?> download(@PathVariable String filename) {
        try {
            Path path = Paths.get("src/main/resources/static/media/" + filename);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            InputStreamResource resource = new InputStreamResource(Files.newInputStream(path));
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (Exception e) {
            throw new NotFoundException("File with name " + filename);
        }
    }
}
