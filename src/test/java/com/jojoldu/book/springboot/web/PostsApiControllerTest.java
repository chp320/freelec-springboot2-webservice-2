package com.jojoldu.book.springboot.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @Autowired
    private WebApplicationContext context ;

    private MockMvc mvc ;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build() ;
    }

    @LocalServerPort
    private int port ;

    @Autowired
    private TestRestTemplate restTemplate ;

    @Autowired
    private PostsRepository postsRepository ;

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")   // ????????? ?????? ????????? ?????? (ROLE_USER??? ???????????? ????????? ??????)
    public void Posts_????????????() throws Exception {
        // given
        String title = "title" ;
        String content = "content" ;
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build() ;

        String url = "http://localhost:" + port + "/api/v1/posts" ;
        System.out.println(">> url: "  + url) ;

        /* @SpringBootTest ?????? MockMvc??? ??????????????? ??????
         * ?????? when ~ then */
//        // when
//        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class) ;
//
//        // then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK) ;
//        assertThat(responseEntity.getBody()).isGreaterThan(0L) ;
//
//        List<Posts> all = postsRepository.findAll() ;
//        System.out.println(">> all: " + all.get(0).getTitle());
//        System.out.println(">> all: " + all.get(0).getAuthor()) ;
//        assertThat(all.get(0).getTitle()).isEqualTo(title) ;
//        assertThat(all.get(0).getContent()).isEqualTo(content) ;


        // when
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk()) ;

        // then
        List<Posts> all = postsRepository.findAll() ;
        assertThat(all.get(0).getTitle()).isEqualTo(title) ;
        assertThat(all.get(0).getContent()).isEqualTo(content) ;

    }

    @Test
    @WithMockUser(roles = "USER")
    public void Posts_????????????() throws Exception {
        // given
        Posts savedPosts = postsRepository.save(Posts.builder()
                                                        .title("title")
                                                        .content("content")
                                                        .author("author")
                                                        .build()) ;

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2" ;
        String expectedContent = "content2" ;
        System.out.println(">> updateId: " + updateId) ;

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                                                                    .title(expectedTitle)
                                                                    .content(expectedContent)
                                                                    .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId ;
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto) ;

        /* @SpringBootTest ?????? MockMvc??? ??????????????? ??????
         * ?????? when ~ then */
//        // when
//        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class) ;
//
//        // then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK) ;
//        assertThat(responseEntity.getBody()).isGreaterThan(0L) ;
//
//        List<Posts> all = postsRepository.findAll() ;
//        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle) ;
//        assertThat(all.get(0).getContent()).isEqualTo(expectedContent) ;

        // when
        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk()) ;

        // then
        List<Posts> all = postsRepository.findAll() ;
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle) ;
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent) ;
    }

//    @Test
//    public void Posts_????????????() throws Exception {
//        // given : ????????? ?????? ??? ?????? id??? ?????? api ??????..
//        Posts savedPosts = postsRepository.save(Posts.builder()
//                        .title("This is a test.")
//                        .author("This is a test, too.")
//                        .content("This is a test.")
//                .build()) ;
//        Long savedId = savedPosts.getId() ;
//        System.out.println(">> savedId: " + savedId) ;
//
//        PostsResponseDto responseDto = PostsResponseDto.builder().build();
//
//        String url = "http://localhost:" + port + "/api/v1/posts/" + savedId ;
//        System.out.println(">> url: " + url) ;
//
//        // when
//        // ????????? ???????????????... ?????? ??? ?????????????? ??????
//        ResponseEntity<Long> responseEntity = restTemplate.getForEntity(url, responseDto, Long.class) ;
//
//        // then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK) ;
//        assertThat(responseEntity.getBody()).isGreaterThan(0L) ;
//
//        List<Posts> all = postsRepository.findAll() ;
//        System.out.println(">> all: " + all.get(0).getTitle());
//        System.out.println(">> all: " + all.get(0).getAuthor()) ;
//    }
}
