package com.example.my1119.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter

//#extends Timestamped를 해야하는 이유.

public class Tag {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long idx;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST,CascadeType.REMOVE})
// (1) fetch: 로딩타입 선택 옵션. Eager: 즉시로딩. 데이터 필요 없는데도 sql 한번 더 날리게 됨.
// Lazy: 지연로딩. 예를들어, tag에서 article 조회할 때만 sql 날라감. 성능면에서는 이게 나음.  Cascade: 종속성 관련 옵션. Persist: 엮여있는 정보 (외래키) 같이 저장
// remove: 엮여있는애가 지워지면, 나도 지워짐 (중복 체크를 통해 데이터 정합성에 기여..)

    @JoinColumn(name = "article_id", nullable = false)
    public Article article;

    // Tag 하나하나를 row 로 만들어주기 위함.
    //#왜 필요하지?
    public Tag(String name, Article article) {
        this.name = name;
        this.article = article;
    }

//    public Tag(TagRequestDto requestDto, Article article) {
//        this.tagName = requestDto.getTagName();
//        this.article = article;
//    }
    // -> 이렇게 하면 구분자 없이 tag 가 저장됨. 뭉태기가..

    //없어도 되는 애.
//    public Tag(String name) {
//        this.name = name;
//    }
}