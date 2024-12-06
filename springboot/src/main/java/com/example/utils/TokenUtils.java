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
 * Token utility class
 */
@Component
public class TokenUtils {

    private static final Logger log = LoggerFactory.getLogger(TokenUtils.class);

    private static AdminService staticAdminService;
    private static BusinessService staticBusinessService;
    private static UserService staticUserService;

    @Resource
    AdminService adminService;
    @Resource
    BusinessService businessService;
    @Resource
    UserService userService;

    @PostConstruct
    public void setUserService() {
        staticAdminService = adminService;
        staticBusinessService = businessService;
        staticUserService = userService;
    }

    /**
     * Generate token
     */
    public static String createToken(String data, String sign) {
        return JWT.create().withAudience(data) // Save userId-role in the token as payload
                  .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // Token expires in 2 hours
                  .sign(Algorithm.HMAC256(sign)); // Use password as the secret key for the token
    }

    /**
     * Get information of the currently logged-in user
     */
    public static Account getCurrentUser() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader(Constants.TOKEN);
            if (ObjectUtil.isNotEmpty(token)) {
                String userRole = JWT.decode(token).getAudience().get(0);
                String userId = userRole.split("-")[0];  // Get user ID
                String role = userRole.split("-")[1];    // Get role
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
        } catch (Exception e) {
            log.error("Error occurred while getting the current user information", e);
        }
        return new Account();  // Return an empty account object
    }
}

