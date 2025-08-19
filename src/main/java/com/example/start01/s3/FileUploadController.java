package com.example.start01.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final S3Service s3Service;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws Exception {
        String url = s3Service.upload(file);
        return ResponseEntity.ok(url);
    }

    @PostMapping("/upload/profile")
    public ResponseEntity<String> uploadProfile(@RequestParam("file") MultipartFile file, @RequestParam("userId") Integer userId) {
        try {
            String url = s3Service.uploadProfile(file, userId);
            // presigned URL or 저장된 key 반환
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("업로드 실패: " + e.getMessage());
        }
    }
}
