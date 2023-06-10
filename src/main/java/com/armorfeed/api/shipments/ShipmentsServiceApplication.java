package com.armorfeed.api.shipments;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition
public class ShipmentsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShipmentsServiceApplication.class, args);
    }

}
