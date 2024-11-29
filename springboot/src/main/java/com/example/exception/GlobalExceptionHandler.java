package com.example.exception;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.example.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages="com.example.controller")
public class GlobalExceptionHandler {

    private static final Log log = LogFactory.get();

    // Unified exception handling with @ExceptionHandler, mainly for Exception
    @ExceptionHandler(Exception.class)
    @ResponseBody // Return JSON string
    public Result error(HttpServletRequest request, Exception e) {
        log.error("Exception information:", e);
        return Result.error();
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody // Return JSON string
    public Result customError(HttpServletRequest request, CustomException e) {
        return Result.error(e.getCode(), e.getMsg());
    }
}
