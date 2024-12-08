package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Spring Boot application.
 * This class initializes and starts the application.
 */
@SpringBootApplication // Marks this class as a Spring Boot application
@MapperScan ("com.example.mapper") // Specifies the package to scan for MyBatis mappers
public class SpringbootApplication {

    /**
     * The main method to launch the Spring Boot application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Run the application by invoking the Spring Boot SpringApplication.run method
        SpringApplication.run(SpringbootApplication.class, args);
    }
}






