package com.example.common;

import com.example.common.enums.ResultCodeEnum;

/**
 * A generic class to encapsulate API responses.
 * Provides standardized methods for returning success and error results.
 */
public class Result {

    /**
     * The result code indicating success or the type of error.
     */
    private String code;

    /**
     * A descriptive message associated with the result.
     */
    private String msg;

    /**
     * The data payload of the result, if any.
     */
    private Object data;

    /**
     * Private constructor to initialize the result with data.
     *
     * @param data The data payload to be included in the result.
     */
    private Result(Object data) {
        this.data = data;
    }

    /**
     * Default constructor.
     */
    public Result() {
    }

    /**
     * Creates a success result with no data payload.
     *
     * @return A success result.
     */
    public static Result success() {
        Result tResult = new Result();
        tResult.setCode(ResultCodeEnum.SUCCESS.code);
        tResult.setMsg(ResultCodeEnum.SUCCESS.msg);
        return tResult;
    }

    /**
     * Creates a success result with a data payload.
     *
     * @param data The data to be included in the result.
     * @return A success result containing the provided data.
     */
    public static Result success(Object data) {
        Result tResult = new Result(data);
        tResult.setCode(ResultCodeEnum.SUCCESS.code);
        tResult.setMsg(ResultCodeEnum.SUCCESS.msg);
        return tResult;
    }

    /**
     * Creates a generic error result with default error information.
     *
     * @return An error result.
     */
    public static Result error() {
        Result tResult = new Result();
        tResult.setCode(ResultCodeEnum.SYSTEM_ERROR.code);
        tResult.setMsg(ResultCodeEnum.SYSTEM_ERROR.msg);
        return tResult;
    }

    /**
     * Creates an error result with a custom error code and message.
     *
     * @param code The error code.
     * @param msg  The error message.
     * @return An error result containing the provided code and message.
     */
    public static Result error(String code, String msg) {
        Result tResult = new Result();
        tResult.setCode(code);
        tResult.setMsg(msg);
        return tResult;
    }

    /**
     * Creates an error result based on a predefined `ResultCodeEnum`.
     *
     * @param resultCodeEnum The predefined result code and message.
     * @return An error result containing the provided code and message.
     */
    public static Result error(ResultCodeEnum resultCodeEnum) {
        Result tResult = new Result();
        tResult.setCode(resultCodeEnum.code);
        tResult.setMsg(resultCodeEnum.msg);
        return tResult;
    }

    /**
     * Retrieves the result code.
     *
     * @return The result code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the result code.
     *
     * @param code The result code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Retrieves the result message.
     *
     * @return The result message.
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Sets the result message.
     *
     * @param msg The result message to set.
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * Retrieves the data payload.
     *
     * @return The data payload.
     */
    public Object getData() {
        return data;
    }

    /**
     * Sets the data payload.
     *
     * @param data The data payload to set.
     */
    public void setData(Object data) {
        this.data = data;
    }
}
