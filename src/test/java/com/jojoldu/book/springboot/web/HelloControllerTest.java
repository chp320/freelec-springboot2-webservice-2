package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

//import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.is ;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content ;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
/* @WebMvcTest 주석처리 이유:
 * @WebMvcTest 는 @ControllerAdvice, @Controller를 읽고,
 * @Repository, @Service, @Component는 스캔 대상이 아니다.
 * SpringSecurity 처리를 위한 SecurityConfig는 읽었지만,
 * 이를 생성하기 위해 필요한 CustomOAuth2UserService를 읽을 수 없어서 아래 에러 발생
 * 에러
 * No qualifying bean of type 'com.jojoldu.book.springboot.config.auth.CustomOAuth2UserService' available
 * 따라서 아예 SecurityConfig를 스캔하지 않도록 수정
 */
//@WebMvcTest(controllers = HelloController.class)
@WebMvcTest(controllers = HelloController.class, excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc ;

    @Test
    @WithMockUser(roles = "USER")
    public void hello가_리턴된다() throws Exception {
        String hello = "hello" ;

        mvc.perform(get("/hello"))      // MockMvc를 통해 /hello 주소로 HTTP get 요청을 함
                .andExpect(status().isOk())        // mvc.perform의 결과 검증. OK 즉, 200 인지 아닌지를 검증
                .andExpect(content().string(hello)) ;   // mvc.perform의 결과 검증. controller에서 "hello"를 리턴하기 때문에 인자로 전달된 hello값이 맞는지 검증
    }

    @Test
    @WithMockUser(roles = "USER")
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello" ;
        int amount = 1000 ;

        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount))) ;

    }

}
