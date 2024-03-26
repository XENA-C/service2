package org.example.api.resolver;

import lombok.RequiredArgsConstructor;
import org.example.api.common.annotation.UserSession;
import org.example.api.domain.user.model.User;
import org.example.api.domain.user.service.UserService;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class UserSessionResolver implements HandlerMethodArgumentResolver {
// request 요청 들어오면 resolver 실행(aop 방식)
    private final UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 지원하는 파라미터 체크 , 어노테이션 체크 -> 세션, 객체 정보 확인

        //1. 어노테이션이 있는지 체크
        var annotation = parameter.hasParameterAnnotation(UserSession.class);

        //2. 파라미터의 타입 체크
        var parameterType = parameter.getParameterType().equals(User.class);

        return (annotation && parameterType); //둘 다 true 일 때 아래 메소드 실행
    }

    @Override         // support parameter true 반환 --> user 정보 생성
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        // request context holder에서 찾아오기
        var requestContext = RequestContextHolder.getRequestAttributes();
        var userId = requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);

        // interceptor 거친 userId -> userEntity 반환
        var userEntity = userService.getUserWithThrow(Long.parseLong(userId.toString()));

        // User 객체 생성: userEntity 정보 담기
        return User.builder()
            .id(userEntity.getId())
            .name(userEntity.getName())
            .email(userEntity.getEmail())
            .status(userEntity.getStatus())
            .password(userEntity.getPassword())
            .address(userEntity.getAddress())
            .registeredAt(userEntity.getRegisteredAt())
            .unregisteredAt(userEntity.getUnregisteredAt())
            .lastLoginAt(userEntity.getLastLoginAt())
            .build()
            ;
    }
}
/* 헤더를 통해 아이디 정보 확인하는 절차를 분리 -> 각자의 로직에 충실하도록
* */