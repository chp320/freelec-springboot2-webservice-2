package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

//import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.is ;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content ;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc ;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello" ;

        mvc.perform(get("/hello"))      // MockMvc를 통해 /hello 주소로 HTTP get 요청을 함
                .andExpect(status().isOk())        // mvc.perform의 결과 검증. OK 즉, 200 인지 아닌지를 검증
                .andExpect(content().string(hello)) ;   // mvc.perform의 결과 검증. controller에서 "hello"를 리턴하기 때문에 인자로 전달된 hello값이 맞는지 검증
    }

    @Test
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
