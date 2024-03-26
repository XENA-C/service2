package org.example.api.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.api.common.annotation.UserSession;
import org.example.api.common.api.Api;
import org.example.api.domain.user.business.UserBusiness;
import org.example.api.domain.user.controller.model.UserResponse;
import org.example.api.domain.user.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserBusiness userBusiness;

    @GetMapping("/me")
    public Api<UserResponse> me(
       @UserSession User user
       //파라미터에 달린 어노테이션과 , 파라미터 타입 --> resolver 에서 체크 true -> interceptor(header token 검증) --> userId 가져와서 UserEntity 생성
       //사용자에 관한 로직 처리 시 별도로 유저 불러올 필요 없다
    ){
        var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
       // request 마다 requestContext 생성

        var userId = requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);

        var response = userBusiness.me(user);
//        var response = userBusiness.me(Long.parseLong(userId.toString()));
        return Api.OK(response);
    }
}

/*
* api 요청 시 modeHeader 툴이 modify (-> request header 값을 세팅해줌)
* 발행한 토큰 확인 -> authorization interceptor
* */

/*
토큰에 들어있는 사용자 정보를 통해
별도 로그인이나 파라미터를 받지 않아도 리퀘스트 헤더에 들어있는 사용자토큰으로 인증처리
(--> 내가 발행한 토큰인지 확인. 유효한 사용자인지는 별도 체크)
토큰 발행은 언제??왜???
* */