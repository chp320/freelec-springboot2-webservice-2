package com.jojoldu.book.springboot.domain.user;

import com.jojoldu.book.springboot.domain.posts.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id // 해당 테이블의 PK필드를 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false)
    private String name ;

    @Column(nullable = false)
    private String email ;

    @Column
    private String picture ;

    @Enumerated(EnumType.STRING)    // JPA로 DB 저장 시 Enum 값을 어떤 형태로 저장할지 결정. 기본적으로는 int형 숫자로 저장되는데 의미를 알 수 없으니 String 형태로 지정
    @Column(nullable = false)
    private Role role ;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name ;
        this.email = email ;
        this.picture = picture ;
        this.role = role ;
    }

    public User update(String name, String picture) {
        this.name = name ;
        this.picture = picture ;

        return this ;
    }

    public String getRoleKey() {
        return this.role.getKey() ;
    }
}
