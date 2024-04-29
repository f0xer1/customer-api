package com.example.customerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * The main entry point of the CustomerAPI Spring Boot application.
 * This class is responsible for bootstrapping the application and launching the embedded server.
 */
@SpringBootApplication
public class CustomerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApiApplication.class, args);
    }

}
