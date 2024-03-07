package org.example.api.account;

import lombok.RequiredArgsConstructor;
import org.example.db.account.AccountEntity;
import org.example.db.account.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountApiController {

    private final AccountRepository accountRepository;

    @GetMapping("/me")
    public void save(){
        var account = AccountEntity.builder().build();
        accountRepository.save(account);
//    public AccountMeResponse me(){
//
//        return AccountMeResponse.builder()
//            .name("홍길동")
//            .email("A@gmail.com")
//            .registeredAt(LocalDateTime.now())
//            .build();
    }
}
