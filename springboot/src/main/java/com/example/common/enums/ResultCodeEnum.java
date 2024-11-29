package com.example.common.enums;

public enum ResultCodeEnum {
    SUCCESS("200", "Success"), // Operation succeeded

    PARAM_ERROR("400", "Parameter error"), // Invalid parameter
    TOKEN_INVALID_ERROR("401", "Invalid token"), // The token is invalid
    TOKEN_CHECK_ERROR("401", "Token verification failed, please log in again"), // Token verification failure
    PARAM_LOST_ERROR("4001", "Missing parameter"), // A required parameter is missing

    SYSTEM_ERROR("500", "System error"), // General system exception
    USER_EXIST_ERROR("5001", "Username already exists"), // The username is already in use
    USER_NOT_LOGIN("5002", "User not logged in"), // The user is not authenticated
    USER_ACCOUNT_ERROR("5003", "Incorrect username or password"), // Login failed due to incorrect credentials
    USER_NOT_EXIST_ERROR("5004", "User does not exist"), // The specified user could not be found
    PARAM_PASSWORD_ERROR("5005", "Incorrect original password"), // The entered original password is incorrect
    COLLECT_ALREADY_ERROR("5006", "You have already collected this item, please do not repeat"), // Duplicate collection attempt
    ;


    public String code;
    public String msg;

    ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
