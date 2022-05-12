package com.jojoldu.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)  // 이 어노테이션이 생성될 수 있는 위치 지정. PARAMETER: 메소드의 파라미터로 선언된 객체에서만 사용 가능
@Retention(RetentionPolicy.RUNTIME)
// @interface : 해당 클래스를 어노테이션 클래스로 지정. 즉, @LoginUser 어노테이션 으로 사용 가능
public @interface LoginUser {
}
