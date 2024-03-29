package org.example.api.domain.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.api.common.api.Api;
import org.example.api.domain.store.business.StoreBusiness;
import org.example.api.domain.store.controller.model.StoreRegisterRequest;
import org.example.api.domain.store.controller.model.StoreResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/store")
public class StoreOpenApiController {
    //가맹점 등록: 로그인 사용자 아닌 가맹점 직원이 사용 -> 열린 api

    private final StoreBusiness storeBusiness;

    @PostMapping("/register")
    public Api<StoreResponse> register(
        @Valid //-> validation : @NotBlank @NotNull
        @RequestBody Api<StoreRegisterRequest> request
    ){
        var response = storeBusiness.register(request.getBody());
        return Api.OK(response);
    }





}
