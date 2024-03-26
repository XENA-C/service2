package org.example.api.domain.userordermenu.converter;

import org.example.api.common.annotation.Converter;
import org.example.db.storemenu.StoreMenuEntity;
import org.example.db.userorder.UserOrderEntity;
import org.example.db.userordermenu.UserOrderMenuEntity;

@Converter
public class UserOrderMenuConverter {

    public UserOrderMenuEntity toEntity(
        UserOrderEntity userOrderEntity,
        StoreMenuEntity storeMenuEntity
    ){
        return UserOrderMenuEntity.builder()
            .userOrderId(userOrderEntity.getId())
            .storeMenuId(storeMenuEntity.getId())
            .build()
            ;
    }

}
