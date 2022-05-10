package com.jojoldu.book.springboot.domain.posts;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor  // 기본 생성자 자동 추가. public Posts{} 와 동일 효과
@Entity // 테이블과 링크될 클래스임을 명시. 기본값으로 클래스의 카멜표기법 이름을 언더라인 네이밍(_)으로 테이블 이름을 맵핑 (JPA의 어노테이션)
// 이와 같이 @Entity가 붙은 클래스를 Entity 클래스.라 부른다. Entity 클래스에는 setter 메소드를 만들지 않는다.
public class Posts extends BaseTimeEntity {

    @Id // 해당 테이블의 PK필드를 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙
    private Long id ;

    @Column(length = 500, nullable = false) // 테이블의 컬럼을 의미. 굳이 선언하지 않아도 해당 클래스 내 모든 필드는 컬럼이 된다. 명시하는 이유는 추가로 변경이 필요한 옵션이 있는 경우 사용하기 위함.
    private String title ;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content ;

    private String author ;

    @Builder    // 빌더 패턴 클래스 생성. 생성자에 포함된 필드만 빌더에 포함.
    public Posts(String title, String content, String author) {
        this.title = title ;
        this.content = content ;
        this.author = author ;
    }

    public void update(String title, String content) {
        this.title = title ;
        this.content = content ;
    }
}
