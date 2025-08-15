package com.example.start01.controller;

import com.example.start01.dao.ImageDao;
import com.example.start01.dto.ImageDto;
import com.example.start01.dto.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class ImageController {
    @Autowired
    public ImageDao imageDao;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path file = Paths.get("src/main/resources/static/images").resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header("Content-Disposition", "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/images/{folder}")
    public List<ImageDto> ImageByFolder(@PathVariable String folder){
        return imageDao.ImageByFolder(folder);
    }

    @PostMapping("/menuImageInsertByOwner")
    public String MenuImageInsertByOwner(
            @RequestParam("folder") String folder,
            @RequestParam("filename") MultipartFile file) {

        // 여기서 folder 값: "store1" 이런 식으로 넘어옴
        // file.getOriginalFilename() 등으로 파일명 사용 가능

        ImageDto dto = new ImageDto();
        dto.setFolder(folder);
        dto.setFilename(file.getOriginalFilename());

        imageDao.MenuImageInsertByOwner(dto);

        // 파일 저장 로직이 필요하면 여기에 작성
        return "이미지 삽입 완료!";
    }

}
