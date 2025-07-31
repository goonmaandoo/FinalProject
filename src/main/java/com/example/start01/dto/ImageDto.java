package com.example.start01.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class ImageDto {
    private Integer id;
    private String bucket;
    private String folder;
    private String filename;
}
