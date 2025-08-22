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
            @RequestParam(value = "folder", required = false) String folderParam) throws Exception {

        try {
            // 1. S3 업로드
            FileUploadResult result = s3Service.upload(file, storeId);
            String newFilename = result.getFileName();
            String url = result.getSignedUrl();

            System.out.println("=== 디버깅 정보 ===");
            System.out.println("S3 업로드 완료 - 파일명: " + newFilename);
            System.out.println("받은 storeId: " + storeId);
            System.out.println("받은 folderParam: " + folderParam);

            // 2. DB에 이미지 정보 삽입
            ImageDto dto = new ImageDto();

            // folder 값 확실하게 설정
            String folderValue = "store_" + storeId;
            dto.setFolder(folderValue);
            dto.setFilename(newFilename);

            /* 디버깅용
            System.out.println("DTO 설정 값:");
            System.out.println("- folder: '" + dto.getFolder() + "'");
            System.out.println("- filename: '" + dto.getFilename() + "'");
            System.out.println("- folder가 null인가? " + (dto.getFolder() == null));
            System.out.println("- filename이 null인가? " + (dto.getFilename() == null));
             */

            // null 체크
            if (dto.getFolder() == null || dto.getFolder().trim().isEmpty()) {
                throw new RuntimeException("folder 값이 비어있습니다.");
            }
            if (dto.getFilename() == null || dto.getFilename().trim().isEmpty()) {
                throw new RuntimeException("filename 값이 비어있습니다.");
            }

            imageDao.MenuImageInsertByOwner(dto);

            System.out.println("DB 저장 후 - 생성된 ID: " + dto.getId());

            // 3. 응답 데이터 구성
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
