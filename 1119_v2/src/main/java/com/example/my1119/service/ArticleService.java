package com.example.my1119.service;

import com.example.my1119.domain.Article;
import com.example.my1119.domain.Comment;
import com.example.my1119.dto.ArticleCommentRequestDto;
import com.example.my1119.dto.ArticleRequestDto;
import com.example.my1119.repository.ArticleRepository;
import com.example.my1119.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public Article setArticle(ArticleRequestDto articleRequestDto) {
        Article article = new Article(articleRequestDto);
        articleRepository.save(article);
        return article;
    }

    public Article getArticle(Long id) {
        return articleRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다"));
    }

    public List<Article> getArticles() {
        return articleRepository.findAll();

    }

    @Transactional
    public void setArticleComment(ArticleCommentRequestDto articleCommentRequestDto) {
        Article article = articleRepository.findById(articleCommentRequestDto.getIdx()).orElseThrow(()
                -> new NullPointerException("해당 아이디가 존재하지 않습니다"));
        Comment comment = new Comment(articleCommentRequestDto, article);
        commentRepository.save(comment);

    }




}
