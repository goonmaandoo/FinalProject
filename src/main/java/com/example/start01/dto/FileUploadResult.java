package com.example.start01.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FileUploadResult {
    private String signedUrl;
    private String fileName;

    public FileUploadResult(String signedUrl, String fileName) {
        this.signedUrl = signedUrl;
        this.fileName = fileName;
    }
}
