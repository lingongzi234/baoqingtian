package com.tjpu22t01.sca_ywl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ScaYwlApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScaYwlApplication.class, args);
    }

}
