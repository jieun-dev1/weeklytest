package com.example.my1119.domain;

import com.example.my1119.dto.ArticleRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

//1) 생성자
//2) 필수로 들어가야 하는 칼럼 지정
//3) Article과 Comment 는 일대다 관계다. Article class에 일대다 관계를 매핑을 해주었지만, DB에서 칼럼으로 선언하진 않았다.
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Article extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long idx;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    //List 처리를 해주지 않으면, Comment should be a container 라는 창이 뜸.
    @OneToMany (mappedBy="article")
    private List<Comment> comments;

    public Article (ArticleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

}
