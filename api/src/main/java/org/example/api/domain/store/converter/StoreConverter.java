package org.example.api.domain.store.converter;

import org.example.api.common.annotation.Converter;
import org.example.api.common.error.ErrorCode;
import org.example.api.common.exception.ApiException;
import org.example.api.domain.store.controller.model.StoreRegisterRequest;
import org.example.api.domain.store.controller.model.StoreResponse;
import org.example.db.store.StoreEntity;

import java.util.Optional;

@Converter
public class StoreConverter {   //request -> entity -> response

    public StoreEntity toEntity(
        StoreRegisterRequest request
    ){
        return Optional.ofNullable(request)
            .map(it ->{
                return StoreEntity.builder()
                        .name(request.getName())
                        .address(request.getAddress())
                        .category(request.getStoreCategory())
                        .minimumAmount(request.getMinimumAmount())
                        .minimumDeliveryAmount(request.getMinimumDeliveryAmount())
                        .thumbnailUrl(request.getThumbnailUrl())
                        .phoneNumber(request.getPhoneNumber())
                        .star(request.getStar())
                        .build()
                    ;
            })
            .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public StoreResponse toResponse(
        StoreEntity entity
    ){
        return Optional.ofNullable(entity)
            .map(it ->{
                return StoreResponse.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .status(entity.getStatus())
                    .category(entity.getCategory())
                    .address(entity.getAddress())
                    .minimumAmount(entity.getMinimumAmount())
                    .minimumDeliveryAmount(entity.getMinimumDeliveryAmount())
                    .thumbnailUrl(entity.getThumbnailUrl())
                    .phoneNumber(entity.getPhoneNumber())
                    .star(entity.getStar())
                    .build();
            })
            .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
