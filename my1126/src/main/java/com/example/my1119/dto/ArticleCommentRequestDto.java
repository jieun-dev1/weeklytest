package com.example.my1119.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleCommentRequestDto {

    private String comment;
    //id 일수도, idx 일수도. 확인해볼것.
    private Long idx;
}
