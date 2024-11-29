package com.example.common.enums;

public enum StatusEnum {
    CHECKING("Under Reviewed"),
    CHECK_OK("Reviewed"),
    CHECK_NO("Review Failed"),
    ;

    public String status;

    StatusEnum(String status) {
        this.status = status;
    }
}
