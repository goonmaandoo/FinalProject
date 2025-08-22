package com.example.start01.s3;

import com.example.start01.dao.ImageDao;
import com.example.start01.dto.FileUploadResult;
import com.example.start01.dto.ImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileUploadController {

    @Autowired
    ImageDao imageDao;

    private final S3Service s3Service;

    @PostMapping("/upload/menuByOwner")
    public ResponseEntity<Map<String, Object>> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("storeId") Integer storeId,
            @RequestParam("folder") String folder) throws Exception {

        try {
            // 1. S3 업로드
            FileUploadResult result = s3Service.upload(file, storeId);
            String newFilename = result.getFileName();
            String url = result.getSignedUrl();

            // 2. DB에 이미지 정보 삽입
            ImageDto dto = new ImageDto();
            dto.setFolder(folder);
            dto.setFilename(newFilename);
            imageDao.MenuImageInsertByOwner(dto);

            // 3. 응답 데이터 구성 (URL + 이미지 ID 반환)
            Map<String, Object> response = new HashMap<>();
            response.put("url", url);
            response.put("imageId", dto.getId());
            response.put("filename", newFilename);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("파일 업로드 및 저장 실패: " + e.getMessage());
        }
    }

    @PostMapping("/upload/storeByOwner")
    public ResponseEntity<String> uploadStore(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id
    ) throws Exception {
        String url = s3Service.uploadStore(file, id);
        return ResponseEntity.ok(url);
    }

//    @PostMapping("/upload/updateMenu")
//    public ResponseEntity<String> updateMenu(@RequestParam("file") MultipartFile file, @RequestParam("store_id") Integer storeId,
//                                             @RequestParam("image_id") Integer imageId
//    ) throws Exception {
//        String url = s3Service.updateMenu(file, storeId, imageId);
//        return ResponseEntity.ok(url);
//    }

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
