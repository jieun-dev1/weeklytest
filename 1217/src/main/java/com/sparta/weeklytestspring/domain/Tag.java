package com.sparta.weeklytestspring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Tag extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long idx;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    //remove 가 무슨 말이지? 아티클이 없어지려면 태그부터 없어져야 하는데 (오히려 반대 아닌가?)
    //참고로 @ManyToOne도 작동에는 이상 없음. 즉시로딩, 지연로딩을 가능하게 해주는 조건인듯.. (성능?)
    @ManyToOne
//    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST,CascadeType.REMOVE})
    // (1) fetch: 로딩타입 선택 옵션. Eager: 즉시로딩. 데이터 필요 없는데도 sql 한번 더 날리게 됨.
// Lazy: 지연로딩. 예를들어, tag에서 article 조회할 때만 sql 날라감. 성능면에서는 이게 나음.  Cascade: 종속성 관련 옵션. Persist: 엮여있는 정보 (외래키) 같이 저장
// remove: 엮여있는애가 지워지면, 나도 지워짐 (중복 체크를 통해 데이터 정합성에 기여..)


    @JoinColumn(name="article_idx", nullable = false)
    private Article article;

    public Tag(String name, Article article) {
        this.name = name;
        this.article = article;
    }

}
