package com.example.exception;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.example.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Global exception handler to catch and handle exceptions across the application.
 * Applies to controllers in the "com.example.controller" package.
 */
@ControllerAdvice(basePackages="com.example.controller")
public class GlobalExceptionHandler {

    // Logger instance for logging exception details
    private static final Log log = LogFactory.get();

    /**
     * Handles all generic exceptions.
     *
     * @param request HttpServletRequest object to provide request information for HTTP servlets
     * @param e The caught exception
     * @return A standardized error response in JSON format
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody // Return JSON string as the response body
    public Result error(HttpServletRequest request, Exception e) {
        // Log the exception details
        log.error("Exception information:", e);
        // Return a generic error result
        return Result.error();
    }

    /**
     * Handles custom exceptions of type CustomException.
     *
     * @param request HttpServletRequest object to provide request information for HTTP servlets
     * @param e The caught CustomException
     * @return A customized error response with specific error code and message in JSON format
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody // Return JSON string as the response body
    public Result customError(HttpServletRequest request, CustomException e) {
        // Return an error response with the specific code and message from CustomException
        return Result.error(e.getCode(), e.getMsg());
    }
}
