package com.example.common.config;

import cn.hutool.core.util.ObjectUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.common.Constants;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.exception.CustomException;
import com.example.service.AdminService;
import com.example.service.BusinessService;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interceptor for handling JWT authentication.
 * Validates the token and authenticates the user before processing the request.
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    @Resource
    private AdminService adminService;

    @Resource
    private BusinessService businessService;

    @Resource
    private UserService userService;

    /**
     * Intercepts and processes HTTP requests to authenticate the user via JWT.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @param handler  The handler for the request.
     * @return true if the request is authenticated and allowed to proceed, otherwise throws an exception.
     * @throws CustomException If the token is invalid, expired, or user authentication fails.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 1. Retrieve the token from the HTTP request header
        String token = request.getHeader(Constants.TOKEN);
        if (ObjectUtil.isEmpty(token)) {
            // If not found in the header, try to get it from the request parameters
            token = request.getParameter(Constants.TOKEN);
        }

        // 2. Start authentication
        if (ObjectUtil.isEmpty(token)) {
            // If no token is provided, throw an exception
            throw new CustomException(ResultCodeEnum.TOKEN_INVALID_ERROR);
        }

        Account account = null;
        try {
            // Decode the token to extract user ID and role
            String userRole = JWT.decode(token).getAudience().get(0);
            String userId = userRole.split("-")[0];
            String role = userRole.split("-")[1];

            // Query the database to retrieve the user's account details
            if (RoleEnum.ADMIN.name().equals(role)) {
                account = adminService.selectById(Integer.valueOf(userId));
            }
            if (RoleEnum.BUSINESS.name().equals(role)) {
                account = businessService.selectById(Integer.valueOf(userId));
            }
            if (RoleEnum.USER.name().equals(role)) {
                account = userService.selectById(Integer.valueOf(userId));
            }
        } catch (Exception e) {
            // Handle errors during token parsing or user data retrieval
            throw new CustomException(ResultCodeEnum.TOKEN_CHECK_ERROR);
        }

        // 3. Verify if the account exists
        if (ObjectUtil.isNull(account)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }

        try {
            // 4. Validate the token using the user's password as the secret key
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(account.getPassword())).build();
            jwtVerifier.verify(token); // Validate the token
        } catch (JWTVerificationException e) {
            // Handle token verification failure
            throw new CustomException(ResultCodeEnum.TOKEN_CHECK_ERROR);
        }

        // Token is valid, allow the request to proceed
        return true;
    }
}
