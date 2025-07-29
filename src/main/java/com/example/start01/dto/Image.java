package com.example.start01.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Image {
    private Integer id;
    private String bucket;
    private String folder;
    private String fileName;
    private String filePath;
}
