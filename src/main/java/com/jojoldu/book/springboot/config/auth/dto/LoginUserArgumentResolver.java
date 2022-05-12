package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.config.auth.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    // HandlerMethodArgumentResolver 는 조건에 맞는 메소드가 있다면 HandlerMethodArgumentResolver의 구현체 (여기서는 LoginUserArgumentResolver)가 지정한 값으로 해당 메소드의 파라미터로 넘길 수 있다..

    private final HttpSession httpSession ;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null ;
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType()) ;

        return isLoginUserAnnotation && isUserClass ;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("user") ;
    }
}
