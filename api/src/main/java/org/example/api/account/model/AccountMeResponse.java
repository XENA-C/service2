package org.example.api.account.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
//@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
// response 응답 형태를 공통화 --> objectMapper 직접 Config
public class AccountMeResponse {

    private String email;

    private String name;

    private LocalDateTime registeredAt;
}
