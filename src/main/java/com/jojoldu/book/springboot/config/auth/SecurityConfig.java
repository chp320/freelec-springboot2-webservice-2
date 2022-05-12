package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService ;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()   // h2-console 화면 사용하기 위해 csrf 옵션 비활성
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    // antMatchers : 권한 관리 대상 지정하는 옵션. authorizeRequests() 가 선언되어야 사용 가능함
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()  // 전체 열람 권한 부여
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())    // /api/v1/** 주소를 가진 API는 USER 권한 사용자만 사용 가능
                    .anyRequest().authenticated()   // anyRequest 즉, 나머지 URL들은 authenticated()를 통해 인증된 사용자(=로그인한 사용자)에게만 허용
                .and()
                    .logout()   // 로그아웃에 대한 옵션. 로그아웃 시 / 주소로 이동
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint() // OAuth 2 로그인 성공 후 사용자 정보 가져올 정보 설정
                            .userService(customOAuth2UserService) ;     // 로그인 후 고객정보 담을 UserService 인터페이스 구현체 등록
    }
}
