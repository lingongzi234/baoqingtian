package com.tjpu22t01.respondent.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name= "sca-applepro")
public interface ScaAppClient {
    @GetMapping("logic/appletest/hello")
    String sayHello();
}
