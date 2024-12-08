package com.example.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * Web configuration class for customizing Spring MVC settings.
 * Registers a custom interceptor to enforce authentication and access control rules.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private JwtInterceptor jwtInterceptor;

    /**
     * Registers the JwtInterceptor and defines the interception rules.
     * The interceptor is applied to all paths except for explicitly excluded paths.
     *
     * @param registry The registry for adding interceptors.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**") // Apply interceptor to all paths
                .excludePathPatterns("/") // Exclude the root path
                .excludePathPatterns("/login") // Exclude the login endpoint
                .excludePathPatterns("/register") // Exclude the registration endpoint
                .excludePathPatterns("/files/**") // Exclude file access paths
                .excludePathPatterns("/type/**") // Exclude type-related paths
                .excludePathPatterns("/notice/selectAll") // Exclude notice query endpoint
                .excludePathPatterns("/goods/**") // Exclude goods-related paths
                .excludePathPatterns("/comment/selectByGoodsId/**"); // Exclude comment query by goods ID
    }
}
