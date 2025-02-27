package com.tjpu22t01.scaapplepro.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "sca-gm")
public interface ScaGmClient {
    @GetMapping("logic/auth/current")
    String gmTest();
}
