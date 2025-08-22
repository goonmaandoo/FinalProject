package com.example.start01.controller;

import com.example.start01.dao.ImageDao;
import com.example.start01.dto.FileUploadResult;
import com.example.start01.dto.ImageDto;
import com.example.start01.dto.MenuDto;
import com.example.start01.s3.S3Service;
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

    @Autowired
    S3Service s3Service;

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
            @RequestParam("filename") MultipartFile file,
            @RequestParam("storeId") Integer storeId) {
        try {
            // 이제 s3Service.upload() 호출 가능
            FileUploadResult uploadResult = s3Service.upload(file, storeId);
            String newFilename = uploadResult.getFileName();

            ImageDto dto = new ImageDto();
            dto.setFolder(folder);
            dto.setFilename(newFilename);
            imageDao.MenuImageInsertByOwner(dto);

            return ResponseEntity.ok(dto.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("파일 저장 실패: " + e.getMessage());
        }
    }

    @PostMapping("/menuImageUpdateByOwner")
    public ResponseEntity<Integer> MenuImageUpdateByOwner(
            @RequestParam("filename") MultipartFile file,
            @RequestParam("id") Integer id) {
        try {
            // 이제 s3Service.upload() 호출 가능
            FileUploadResult uploadResult = s3Service.update(file, id);
            String newFilename = uploadResult.getFileName();

            ImageDto dto = new ImageDto();
            dto.setFilename(newFilename);
            imageDao.ImageUpdateByOwner(dto);

            return ResponseEntity.ok(dto.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("파일 저장 실패: " + e.getMessage());
        }
    }

}
