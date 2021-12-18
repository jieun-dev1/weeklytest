package com.example.my1119.controller;

import com.example.my1119.domain.Article;
import com.example.my1119.dto.ArticleCommentRequestDto;
import com.example.my1119.dto.ArticleRequestDto;
import com.example.my1119.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController

public class ArticleController {
    private final ArticleService articleService;


    @PostMapping("/article")
    public Article setArticle(@RequestBody ArticleRequestDto articleRequestDto) {
        return articleService.setArticle(articleRequestDto);
    }
//    @GetMapping("/articles?searchTag=${tag}")
    //requestparam 이 searchTag~ 이 부분을 구현해준다.
    @GetMapping("/articles")
    public List<Article> getArticles(@RequestParam(required = false) String searchTag) {
        return articleService.getArticles(searchTag);
    }

    //전체 목록 list가 되어 올때 tag가 받아지지 않는다.

    @GetMapping("/article/{id}")
    public Article getArticle(@PathVariable Long id) {
        return articleService.getArticle(id);
    }

    @PostMapping("/article/comment")
    public void setArticleComment(@RequestBody ArticleCommentRequestDto articleCommentRequestDto, ArticleRequestDto articleRequestDto) {
        articleService.setArticleComment(articleCommentRequestDto);
    }


}
