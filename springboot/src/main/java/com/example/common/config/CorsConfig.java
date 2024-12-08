package com.example.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Configuration for Cross-Origin Resource Sharing (CORS).
 * Enables the application to handle requests from different origins, allowing cross-origin communication.
 */
@Configuration
public class CorsConfig {

    /**
     * Configures a {@link CorsFilter} bean to handle CORS settings.
     *
     * @return A CorsFilter configured with the defined CORS rules.
     */
    @Bean
    public CorsFilter corsFilter() {
        // Create a source object to manage CORS configuration
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // Define CORS configuration rules
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1. Allow requests from any origin
        corsConfiguration.addAllowedHeader("*"); // 2. Allow all headers in requests
        corsConfiguration.addAllowedMethod("*"); // 3. Allow all HTTP methods (GET, POST, PUT, DELETE, etc.)

        // Apply the CORS configuration to all endpoints
        source.registerCorsConfiguration("/**", corsConfiguration);

        // Return a CorsFilter with the configured source
        return new CorsFilter(source);
    }
}
