package com.sparta.weeklytestspring.repository;

import com.sparta.weeklytestspring.domain.Article;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    // EntityGraph: comments와 tags객체를 같이 로딩해줘.
//    @EntityGraph(attributePaths = {"comments","tags"})
    List<Article> findAllByTagsName(String name);
    List<Article> findAllByTitleOrContent(String title, String content);

    //둘다 쓰고 싶으니까 재 정의 @Override를 하는거겠지?
//    @EntityGraph(attributePaths = {"comments","tags"})
    @Override
    List<Article> findAll();
}
