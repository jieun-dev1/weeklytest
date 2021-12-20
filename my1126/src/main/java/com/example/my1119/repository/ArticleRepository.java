package com.example.my1119.repository;

import com.example.my1119.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
//    List<Article> findAllByTagsName(String name);
    List<Article> findAllByTagsName(String searchTag);
}
