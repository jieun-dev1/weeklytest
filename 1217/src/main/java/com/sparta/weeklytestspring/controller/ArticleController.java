package com.sparta.weeklytestspring.controller;

import com.sparta.weeklytestspring.domain.Article;
import com.sparta.weeklytestspring.dto.ArticleCommentRequestDto;
import com.sparta.weeklytestspring.dto.ArticleRequestDto;
import com.sparta.weeklytestspring.dto.UserDto;
import com.sparta.weeklytestspring.service.ArticleService;
//import com.sparta.weeklytestspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleService articleService;
//    private final UserService userService;

    @PostMapping("/article")
    public Article setArticle(ArticleRequestDto articleRequestDto) throws IOException {
        return articleService.setArticle(articleRequestDto);
    }

    @GetMapping("/articles")
    public List<Article> getArticles(@RequestParam(required = false) String searchTag){
        return articleService.getArticles(searchTag);
    }

    @GetMapping("/article/{id}")
    public Article getArticle(@PathVariable Long id){
        return articleService.getArticle(id);
    }


    @PostMapping("/article/comment")
    public void setArticleComment(@RequestBody ArticleCommentRequestDto articleCommentRequestDto){
        articleService.setArticleComment(articleCommentRequestDto);
    }

//    @PostMapping("/article/signup")
//    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) throws Exception {
//        return userService.registerUser(userDto);
//    }
}
