package com.tjpu22t01.respondent.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="sca-gm")
public interface ScaGmClient {
    @GetMapping("logic/gmtest/hello")
        String gmtestHello();
}
