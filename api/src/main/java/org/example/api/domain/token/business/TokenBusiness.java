package org.example.api.domain.token.business;

import lombok.RequiredArgsConstructor;
import org.example.api.common.annotation.Business;
import org.example.api.common.error.ErrorCode;
import org.example.api.common.exception.ApiException;
import org.example.api.domain.token.controller.model.TokenResponse;
import org.example.api.domain.token.converter.TokenConverter;
import org.example.api.domain.token.service.TokenService;
import org.example.db.user.UserEntity;

import java.util.Optional;

@RequiredArgsConstructor
@Business
public class TokenBusiness {

    private final TokenService tokenService;
    private final TokenConverter tokenConverter;

    /**
     * 1. user entity user Id 추출
     * 2. access, refresh token 발행
     * 3. converter -> token response로 변경
     */
    public TokenResponse issueToken(UserEntity userEntity){

        return Optional.ofNullable(userEntity)
            .map(ue -> { //UserEntity
                return ue.getId();
            })
            .map(userId -> { //토큰 발행
                var accessToken = tokenService.issueAccessToken(userId);
                var refreshToken = tokenService.issueRefreshToken(userId);
                return tokenConverter.toResponse(accessToken, refreshToken);
            })
            .orElseThrow(
                ()-> new ApiException(ErrorCode.NULL_POINT)
            );
    }

    //토큰 검증
    public Long validationAccessToken(String accessToken){
        var userId = tokenService.validationToken(accessToken);
        return userId;
    }

}
