package com.sparta.weeklytestspring.domain;

import com.sparta.weeklytestspring.dto.ArticleRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Article extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idx;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;


    @Column(nullable = false)
    private String content;

    //imageUrl이 추가됨.
//    @Column
    @Column(nullable = true)
    private String imageUrl;

    @OneToMany(mappedBy="article")
    private List<Comment> comments;

    //List -> set이 됨. 왜?
    @OneToMany(mappedBy="article")
    private Set<Tag> tags;

    //imageUrl이 들어가는 생성자 추가.
    public Article(ArticleRequestDto requestDto, String imageUrl) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.username = requestDto.getUsername();
        this.imageUrl = imageUrl;
    }

    //얘가 없어도 작동할까?
//    public Article(ArticleRequestDto requestDto) {
//        this.title = requestDto.getTitle();
//        this.content = requestDto.getContent();
//    }
}
