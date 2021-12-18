package com.sparta.weeklytestspring.service;

import com.sparta.weeklytestspring.domain.Article;
import com.sparta.weeklytestspring.domain.Comment;
import com.sparta.weeklytestspring.domain.Tag;
import com.sparta.weeklytestspring.dto.ArticleCommentRequestDto;
import com.sparta.weeklytestspring.dto.ArticleRequestDto;
import com.sparta.weeklytestspring.repository.ArticleRepository;
import com.sparta.weeklytestspring.repository.CommentRepository;
import com.sparta.weeklytestspring.repository.TagRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//reflect.Array -> IOException 으로 바뀜.

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final TagRepository tagRepository;
    private final AwsService awsService;

    //IO Exception 이 왜 추가 되었지? throws는 예외가 발생되더라도 넘긴다.
    @Transactional
    public Article setArticle(ArticleRequestDto articleRequestDto) throws IOException {
        String url = null;
        if(articleRequestDto.getImage() != null) url = awsService.upload(articleRequestDto.getImage());
        Article article = new Article(articleRequestDto, url);
        System.out.println(article);
        articleRepository.save(article);

        List<String> items = Arrays.asList(articleRequestDto.getTags().split("\\s*,\\s*"));
        List<Tag> tags = items.stream().map(tag -> new Tag(tag, article)).collect(Collectors.toList());
//map = 요소들을 특정 조건에 해당하는 값으로 변환하여 줌. 여기서는 article 객체를 기준으로 입력된 tag를 List로 모아 새로운 List를 만들겠다는 의미
//        List<Tag> tags = items.stream().map(tag -> new Tag(tag, article)).collect(Collectors.toList());
        System.out.println(tags);
        tagRepository.saveAll(tags);
        System.out.println(tags);

        return article;
    }

    public Article getArticle(Long id){
        return articleRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
    }

    public List<Article> getArticles(String searchTag){
        if(searchTag.isEmpty()){
            return articleRepository.findAll();
        } else {
            return articleRepository.findAllByTagsName(searchTag);
        }
    }

    @Transactional
    public void setArticleComment(ArticleCommentRequestDto articleCommentRequestDto){
        Article article = articleRepository.findById(articleCommentRequestDto.getIdx()).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        Comment comment = new Comment(articleCommentRequestDto, article);
        commentRepository.save(comment);
    }

}
