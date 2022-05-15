package com.jojoldu.book.springboot.web;

/*
 * This is a controller to judge which port is proper.
 */
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfileController {

    private final Environment env ;

    @GetMapping("/profile")
    public String profile() {
        List<String> profiles = Arrays.asList(env.getActiveProfiles()) ;    // 현재 실행 중인 activeProfile을 모두 가져옴. 실습한 코드에서는 real, oauth, real-db 총 3개가 있음
//        System.out.println(">> profiles: " + profiles.get(0)) ;
//        System.out.println(">> profiles: " + profiles.get(1)) ;
//        System.out.println(">> profiles: " + profiles.get(2)) ;
//        profiles.forEach(System.out::println);
        for(String profile : profiles) {
            System.out.println(">> profile: " + profile);
        }

        List<String> realProfiles = Arrays.asList("real", "real1", "real2") ;
//        System.out.println(">> realProfiles: " + profiles.get(0)) ;
//        System.out.println(">> realProfiles: " + profiles.get(1)) ;
//        System.out.println(">> realProfiles: " + profiles.get(2)) ;
//        realProfiles.forEach(System.out::println);
        for(String realProfile : realProfiles) {
            System.out.println(">> realProfile: " + realProfile);
        }

        // profile 첫 번째 row에 적재된 값을 defaultProfile로 설정
        String defaultProfile = profiles.isEmpty()? "default" : profiles.get(0) ;
        System.out.println(">> defaultProfile: " + defaultProfile) ;

        return profiles.stream()
                .filter(realProfiles::contains)
                .findAny()
                .orElse(defaultProfile) ;
    }
}
