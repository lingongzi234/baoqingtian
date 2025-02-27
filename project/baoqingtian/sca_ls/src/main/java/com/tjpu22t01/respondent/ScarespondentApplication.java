package com.tjpu22t01.respondent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ScarespondentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScarespondentApplication.class, args);
    }

}
