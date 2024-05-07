package org.example.ecommservicecustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EcommServiceCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommServiceCustomerApplication.class, args);
    }

}
