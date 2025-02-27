package com.tjpu22t01.sca_ywl.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "sca-applepro")
public interface ScaAppleproClient {
    @GetMapping("/logic/appletest/hello")
    String sayHello();
}
