package com.example.common.enums;

/**
 * Enumeration representing various result codes and their associated messages.
 * Used to standardize API responses and error handling throughout the system.
 */
public enum ResultCodeEnum {

    /**
     * Operation succeeded.
     */
    SUCCESS("200", "Success"),

    /**
     * Parameter-related errors.
     */
    PARAM_ERROR("400", "Parameter error"), // Invalid parameter
    TOKEN_INVALID_ERROR("401", "Invalid token"), // The token is invalid
    TOKEN_CHECK_ERROR("401", "Token verification failed, please log in again"), // Token verification failure
    PARAM_LOST_ERROR("4001", "Missing parameter"), // A required parameter is missing

    /**
     * System-level errors.
     */
    SYSTEM_ERROR("500", "System error"), // General system exception

    /**
     * User-related errors.
     */
    USER_EXIST_ERROR("5001", "Username already exists"), // The username is already in use
    USER_NOT_LOGIN("5002", "User not logged in"), // The user is not authenticated
    USER_ACCOUNT_ERROR("5003", "Incorrect username or password"), // Login failed due to incorrect credentials
    USER_NOT_EXIST_ERROR("5004", "User does not exist"), // The specified user could not be found
    PARAM_PASSWORD_ERROR("5005", "Incorrect original password"), // The entered original password is incorrect

    /**
     * Collection-related errors.
     */
    COLLECT_ALREADY_ERROR("5006", "You have already collected this item, please do not repeat"); // Duplicate collection attempt

    /**
     * The unique code representing the result or error.
     */
    public String code;

    /**
     * The human-readable message associated with the result or error.
     */
    public String msg;

    /**
     * Constructor to initialize the code and message for each enum value.
     *
     * @param code The unique code for the result or error.
     * @param msg  The descriptive message for the result or error.
     */
    ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

