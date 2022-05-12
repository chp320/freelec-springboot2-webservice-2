package com.jojoldu.book.springboot;
import org.springframework.boot.SpringApplication ;
import org.springframework.boot.autoconfigure.SpringBootApplication ;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// Application 클래스는 프로젝트의 메인 클래스가 된다 - main()
// @SpringBootApplication 으로 스프링 부트의 자동 설정, 스프링 bean 읽기/생성 모두 자동 설정함
//@EnableJpaAuditing  // JPA Auditing 기능 활성화 -> p.221 오류 발생으로 삭제
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args) ;    // run() 으로 내장 WAS를 실행함. 톰캣과 같은 외장 WAS를 설치하지 않아도 jar 파일만으로도 실행 가능함.
    }
}
