package org.example.api.account;

import lombok.RequiredArgsConstructor;
import org.example.api.account.model.AccountMeResponse;
import org.example.api.common.api.Api;
import org.example.api.common.error.ErrorCode;
import org.example.api.common.error.UserErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountApiController {

  //  private final AccountRepository accountRepository;

    @GetMapping("/me")
    public Api<Object> me(){  //Api --> 성공 or 실패
// controller+Service --> 성공케이스만 처리, 예외: ExceptionHandler 처리
// var account = AccountEntity.builder().build();
// accountRepository.save(account);

    var response =  AccountMeResponse.builder()
            .name("홍길동")
            .email("A@gmail.com")
            .registeredAt(LocalDateTime.now())
            .build();

            var str = "안녕하세요";
            var age = Integer.parseInt(str);
            Integer.parseInt(str);
          /*  try {
                age = Integer.parseInt(str);
                return ResponseEntity
                        .status(200)
                        .body(Api.OK(response));  //성공

            }catch (Exception e){
                return ResponseEntity
                        .status(500)
                        .body(Api.ERROR(ErrorCode.SERVER_ERROR)); //실패
            }*/

         return Api.OK(response);
        //  return Api.ERROR(UserErrorCode.USER_NOT_FOUND, "홍길동 없음");
        //  return Api.OK(response);
    }
}
