package com.example.my1119.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleRequestDto {
    private String title;
    private String content;
    private String tags;
}
