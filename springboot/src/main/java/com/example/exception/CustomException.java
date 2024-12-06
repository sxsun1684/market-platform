package com.example.exception;

import com.example.common.enums.ResultCodeEnum;

/**
 * CustomException class represents a user-defined exception for specific application errors.
 */
public class CustomException extends RuntimeException {
    // Error code
    private String code;

    // Error message
    private String msg;

    /**
     * Constructor to create a CustomException using an enum value.
     * This allows for consistent error codes and messages.
     *
     * @param resultCodeEnum Enum containing predefined error code and message.
     */
    public CustomException(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.code;
        this.msg = resultCodeEnum.msg;
    }

    /**
     * Constructor to create a CustomException with a specific code and message.
     * This is useful for dynamic error creation.
     *
     * @param code Error code representing the type of error.
     * @param msg  Error message providing additional information about the error.
     */
    public CustomException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * Getter for the error code.
     *
     * @return Error code as a String.
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter for the error code.
     *
     * @param code Error code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter for the error message.
     *
     * @return Error message as a String.
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Setter for the error message.
     *
     * @param msg Error message to set.
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
