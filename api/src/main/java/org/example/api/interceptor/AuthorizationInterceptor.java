package org.example.api.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.api.common.error.ErrorCode;
//import org.example.api.common.error.TokenErrorCode;
import org.example.api.common.error.TokenErrorCode;
import org.example.api.common.exception.ApiException;
import org.example.api.domain.token.business.TokenBusiness;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

/*import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;*/
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final TokenBusiness tokenBusiness;

    @Override //사전 검증
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Authorization Interceptor url : {}", request.getRequestURI());

        // WEB ,chrome -->  GET, POST api 요청 전 OPTION api 요청 -> 해당 메소드를 지원하는지 체크 후 pass
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }

        // 리소스 요청 --> js. html. png resource 요청 시
        if (handler instanceof ResourceHttpRequestHandler) {
            return true; //검증 없이 통과
        }

        //interceptor 등록을 위해 Config 설정


        //TODO header 검증
        var accessToken = request.getHeader("authorization-token");  //헤더값 가져오기

        if(accessToken == null){ //헤더값이 없으면 '토큰 없음' 에러코드
            throw new ApiException(TokenErrorCode.AUTHORIZATION_TOKEN_NOT_FOUND);
        }

        var userId = tokenBusiness.validationAccessToken(accessToken);
        //tokenBusiness 의 validationAccessToken 메소드를 통해 userId 반환
        // userId 없을 시 Business 로직에서 throw -> 반드시 userId 값밧이 존재할 때만 반환

        if(userId != null){ //요청 SCOPE 에 userId 집어넣기
            var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            //local thread : 한 가지 request 를 global 하게 저장할 수 있는 영역 (null 제거 )

            requestContext.setAttribute("userId", userId, RequestAttributes.SCOPE_REQUEST);
            //현재 요청 requestContext 에 userId 저장, 범위: 이번 요청 동안만
            return true;
        }

        throw new ApiException(ErrorCode.BAD_REQUEST, "인증실패");

    }

}
