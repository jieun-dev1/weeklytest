package com.example.my1119.domain;

import com.example.my1119.dto.ArticleCommentRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

//1)Comment 에는 article Column 이 필요하니, 선언해보겠다.
public class Comment {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long idx;

    @Column(nullable = false)
    private String comment;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="article_id", nullable = false)
    private Article article;

    //생성자에 article도 들어가야하는데, 왜 들어가야하는지 조금 헷갈림.
    public Comment(ArticleCommentRequestDto articleCommentRequestDto, Article article) {
        this.comment = articleCommentRequestDto.getComment();
        this.article = article;
    }


}
