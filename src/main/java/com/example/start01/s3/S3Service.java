package com.example.start01.s3;

import com.example.start01.dto.FileUploadResult;
import io.awspring.cloud.s3.S3Template;
import io.awspring.cloud.s3.ObjectMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.UUID;

@Service
public class S3Service {

    private final S3Template s3Template;

    @Value("${app.s3.bucket}")
    private String bucket;

    public S3Service(S3Template s3Template) {
        this.s3Template = s3Template;
    }

    // 메뉴이미지
    public FileUploadResult upload(MultipartFile file, Integer storeId) throws Exception {
        // insert만되는거(admin dashboard상단)
        String original = file.getOriginalFilename();
        String ext = (original != null && original.contains(".")) ? original.substring(original.lastIndexOf('.')) : "";
        String uuid = UUID.randomUUID().toString();
        String key = "imgfile/" + "store_" + storeId + "/menu_" + uuid + ext; // requestparam으로 받아오기 이게 경로명
        String fileName = "menu_" + uuid + ext;
        // key가 저장경로 겸 파일이름
        // 저장경로 같고 파일이름 다르면 prefix

        try (InputStream is = file.getInputStream()) {
            // content-type 자동 추론되지만 명시하고 싶으면 ObjectMetadata에 넣어도 됨
            s3Template.upload(
                    bucket,
                    key,
                    is,
                    ObjectMetadata.builder()
                            .contentType(file.getContentType())
                            .build()
            );
        }

        // 버킷을 퍼블릭으로 열지 않았다면, 프론트에서 접근용으로 pre-signed URL을 만들어 반환
        URL signed = s3Template.createSignedGetURL(bucket, key, Duration.ofMinutes(10));
        return new FileUploadResult(signed.toString(), fileName); // 프론트에서 바로 표시/다운로드 가능 (유효기간 10분)
    }

    // 가게대표이미지
    public String uploadStore(MultipartFile file, Integer id) throws Exception {
        // insert만되는거(admin dashboard상단)
        String original = file.getOriginalFilename();
        String ext = (original != null && original.contains(".")) ? original.substring(original.lastIndexOf('.')) : "";
        String key = "imgfile/store/store_" + id + ext; // requestparam으로 받아오기 이게 경로명
        // key가 저장경로 겸 파일이름
        // 저장경로 같고 파일이름 다르면 prefix

        try (InputStream is = file.getInputStream()) {
            // content-type 자동 추론되지만 명시하고 싶으면 ObjectMetadata에 넣어도 됨
            s3Template.upload(
                    bucket,
                    key,
                    is,
                    ObjectMetadata.builder()
                            .contentType(file.getContentType())
                            .build()
            );
        }

        // 버킷을 퍼블릭으로 열지 않았다면, 프론트에서 접근용으로 pre-signed URL을 만들어 반환
        URL signed = s3Template.createSignedGetURL(bucket, key, Duration.ofMinutes(10));
        return signed.toString(); // 프론트에서 바로 표시/다운로드 가능 (유효기간 10분)
    }

    // 메뉴이미지수정
    public FileUploadResult update(MultipartFile file, Integer storeId) throws Exception {
        // insert만되는거(admin dashboard상단)
        String original = file.getOriginalFilename();
        String ext = (original != null && original.contains(".")) ? original.substring(original.lastIndexOf('.')) : "";
        String uuid = UUID.randomUUID().toString();
        String key = "imgfile/" + "store_" + storeId + "/menu_" + uuid + ext; // requestparam으로 받아오기 이게 경로명
        String fileName = "menu_" + uuid + ext;
        // key가 저장경로 겸 파일이름
        // 저장경로 같고 파일이름 다르면 prefix

        try (InputStream is = file.getInputStream()) {
            // content-type 자동 추론되지만 명시하고 싶으면 ObjectMetadata에 넣어도 됨
            s3Template.upload(
                    bucket,
                    key,
                    is,
                    ObjectMetadata.builder()
                            .contentType(file.getContentType())
                            .build()
            );
        }

        // 버킷을 퍼블릭으로 열지 않았다면, 프론트에서 접근용으로 pre-signed URL을 만들어 반환
        URL signed = s3Template.createSignedGetURL(bucket, key, Duration.ofMinutes(10));
        return new FileUploadResult(signed.toString(), fileName); // 프론트에서 바로 표시/다운로드 가능 (유효기간 10분)
    }

    public String uploadProfile(MultipartFile file, Integer userId) throws Exception {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("업로드할 파일이 없습니다.");
        }

        String original = file.getOriginalFilename();
        String ext = (original != null && original.contains(".")) ?
                original.substring(original.lastIndexOf('.')) : "";

        //프로필 이미지 경로
        String key = "profileimg/" + userId + "/profile" + ext;

        try (InputStream is = file.getInputStream()) {
            s3Template.upload(
                    bucket,
                    key,
                    is,
                    ObjectMetadata.builder()
                            .contentType(file.getContentType()).build());
        }
        return key;
    }

    public void delete(String key) {
        s3Template.deleteObject(bucket, key);
    }
}
