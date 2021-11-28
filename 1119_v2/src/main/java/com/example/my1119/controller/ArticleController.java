package com.example.my1119.controller;

import com.example.my1119.domain.Article;
import com.example.my1119.domain.Comment;
import com.example.my1119.dto.ArticleCommentRequestDto;
import com.example.my1119.dto.ArticleRequestDto;
import com.example.my1119.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/articles")
    public List<Article> getArticles() {
        return articleService.getArticles();
    }

    @GetMapping("/article/{id}")
    public Article getArticle(@PathVariable Long id) {
        return articleService.getArticle(id);
    }

    @PostMapping("/article/comment")
    public void setArticleComment(@RequestBody ArticleCommentRequestDto articleCommentRequestDto) {
        articleService.setArticleComment(articleCommentRequestDto);
    }

}
