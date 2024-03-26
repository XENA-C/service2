package org.example.db.userordermenu.enums;

public enum UserOrderMenuStatus {

    REGISTERED("등록"),
    UNREGISTERED("해지"),
    ;

    UserOrderMenuStatus(String description){
        this.description = description;
    }
    private String description;
}
