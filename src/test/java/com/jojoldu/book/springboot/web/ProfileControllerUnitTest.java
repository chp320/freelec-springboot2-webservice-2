package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.context.annotation.Profile;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProfileControllerUnitTest {

    @Test
    public void real_profile이_조회된다() {
        // given
        String expectedProfile = "real" ;
        MockEnvironment env = new MockEnvironment() ;
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env) ;

        // when
        String profile = controller.profile();  // ProfileController에서 적재한 첫 번째 프로필이 조회된다.
        /*
            profiles: real
            profiles: oauth
            profiles: real-db
         */
        System.out.println(">> profile: " + profile) ;

        // then
        assertThat(profile).isEqualTo(expectedProfile) ;
    }

    @Test
    public void real_profile이_없으면_첫_번째가_조회된다() {
        // given
        String expectedProfile = "oauth" ;
        MockEnvironment env = new MockEnvironment() ;
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env) ; // oauth, real-db가 적재됨

        // when
        String profile = controller.profile() ;

        // then
        assertThat(profile).isEqualTo(expectedProfile) ;
    }

    @Test
    public void active_profile이_없으면_default가_조회된다() {
        // given
        String expectedProfile = "default" ;
        MockEnvironment env = new MockEnvironment() ;
        // env에 적재할 프로필이 없음. ProfileController 바로 생성
        ProfileController controller = new ProfileController(env) ;

        // when
        String profile = controller.profile() ;

        // then
        assertThat(profile).isEqualTo(expectedProfile) ;
    }
}
