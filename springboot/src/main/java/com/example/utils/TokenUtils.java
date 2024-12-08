package com.example.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.common.Constants;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.service.AdminService;
import com.example.service.BusinessService;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Utility class for handling JWT tokens.
 * Provides methods to create tokens and retrieve information about the currently logged-in user.
 */
@Component
public class TokenUtils {

    private static final Logger log = LoggerFactory.getLogger(TokenUtils.class);

    // Static references to the services for user roles
    private static AdminService staticAdminService;
    private static BusinessService staticBusinessService;
    private static UserService staticUserService;

    @Resource
    AdminService adminService; // Admin service dependency
    @Resource
    BusinessService businessService; // Business service dependency
    @Resource
    UserService userService; // User service dependency

    /**
     * Initializes static service instances after dependency injection.
     * This ensures that static methods can access the injected services.
     */
    @PostConstruct
    public void setUserService() {
        staticAdminService = adminService;
        staticBusinessService = businessService;
        staticUserService = userService;
    }

    /**
     * Creates a JWT token with the specified data and signature.
     *
     * @param data The payload to be included in the token (e.g., userId-role).
     * @param sign The secret key used for signing the token.
     * @return A JWT token string.
     */
    public static String createToken(String data, String sign) {
        return JWT.create()
                  .withAudience(data) // Store the payload in the token
                  .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // Set token expiration to 2 hours
                  .sign(Algorithm.HMAC256(sign)); // Sign the token with the provided secret key
    }

    /**
     * Retrieves the information of the currently logged-in user based on the token.
     *
     * @return The account object of the current user, or an empty account object if an error occurs.
     */
    public static Account getCurrentUser() {
        try {
            // Get the current HTTP request
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            // Retrieve the token from the request header
            String token = request.getHeader(Constants.TOKEN);
            if (ObjectUtil.isNotEmpty(token)) {
                // Decode the token to extract user role and ID
                String userRole = JWT.decode(token).getAudience().get(0);
                String userId = userRole.split("-")[0]; // Extract the user ID
                String role = userRole.split("-")[1]; // Extract the user role

                // Return the user account based on their role
                if (RoleEnum.ADMIN.name().equals(role)) {
                    return staticAdminService.selectById(Integer.valueOf(userId));
                }
                if (RoleEnum.BUSINESS.name().equals(role)) {
                    return staticBusinessService.selectById(Integer.valueOf(userId));
                }
                if (RoleEnum.USER.name().equals(role)) {
                    return staticUserService.selectById(Integer.valueOf(userId));
                }
            }
        } catch(Exception e) {
            // Log the error if retrieving user information fails
            log.error("Error occurred while getting the current user information", e);
        }
        // Return an empty account object if no user information is found
        return new Account();
    }
}
