package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.service.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService ;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc()) ;
        return "index" ;    // 머스테치 스타터 덕분에 컨트롤러에서 문자열 반환 시, 앞의 경로 및 뒤의 파일 확장자는 자동 지정됨.
                            // 앞 경로: src/main/resources/templates
                            // 뒤 확장자: .mustache
    }

    @GetMapping("/posts/save")
    public String postSave() {
        // /posts/save 를 호출하면 앞 경로/뒤 파일 확장자가 붙어서 리턴한다.
        // src/main/resources/templates/posts-save.mustache
        return "posts-save" ;
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id) ;
        model.addAttribute("post", dto) ;

        return "posts-update" ;
    }


}
