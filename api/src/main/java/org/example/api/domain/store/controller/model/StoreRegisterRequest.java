package org.example.api.domain.store.controller.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.db.store.enums.StoreCategory;


import java.math.BigDecimal;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreRegisterRequest {

    @NotBlank
    private String name;

    @NotBlank   // "" , " " , null
    private String address;

    @NotNull
    private StoreCategory storeCategory;

    @NotBlank
    private String thumbnailUrl;

    private double star;

    @NotNull
    private BigDecimal minimumAmount;

    @NotNull
    private BigDecimal minimumDeliveryAmount;

    @NotBlank
    private String phoneNumber;

}
