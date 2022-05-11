package com.jojoldu.book.springboot.web;

/*
   This is a class for test.
 */


import com.jojoldu.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 컨트롤러를 JSON을 반환하는 컨트롤러로 만듦.
// 과거 @ResponseBody를 각 메소드마다 선언했던 것을 한 번에 사용할 수 있게 해줌.
public class HelloController {
    @GetMapping("/hello")
    // HTTP method인 Get 요청을 받을 수 있는 API 생성.
    // 예전에는 @RequestMapping(method=RequestMethod.GET)으로 사용되던 것임.
    public String hello() {
        return "hello" ;
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amout) {
        return new HelloResponseDto(name, amout) ;
    }
}
