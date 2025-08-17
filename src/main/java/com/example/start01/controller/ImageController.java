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

import java.io.File;
import java.io.IOException;
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
    public ResponseEntity<Integer> MenuImageInsertByOwner(
            @RequestParam("folder") String folder,
            @RequestParam("filename") MultipartFile file) {

        try {
            // 1. 파일 저장
            String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/image/" + folder + "/";
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 2. 새로운 파일명 생성 (중복 방지)
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = System.currentTimeMillis() + "_" + originalFilename;

            // 3. 실제 파일 저장
            File saveFile = new File(uploadDir + newFilename);
            file.transferTo(saveFile);

            // 4. DB에 이미지 정보 저장
            ImageDto dto = new ImageDto();
            dto.setFolder(folder);
            dto.setFilename(newFilename); // 새로운 파일명 사용

            imageDao.MenuImageInsertByOwner(dto);

            // 5. 생성된 이미지 ID 반환 (MyBatis가 자동으로 dto.id에 설정함)
            return ResponseEntity.ok(dto.getId());

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("파일 저장 실패: " + e.getMessage());
        }
    }

}
