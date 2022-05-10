package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// ibatis나 MyBatis 등에서 DAO라 불리는 DB Layer 접근자로 JpaRepository<Entity 클래스, PK 타입> 를 상속한다.
// Entity 클래스와 기본 Entity Repository는 함께 위치해야 한다!
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
