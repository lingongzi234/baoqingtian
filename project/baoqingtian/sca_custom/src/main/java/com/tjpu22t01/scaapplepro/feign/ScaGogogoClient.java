package com.tjpu22t01.scaapplepro.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "sca-gogogo")
public interface ScaGogogoClient {
    @GetMapping("logic/gogogo/gogogo")
    String gogogoTest();
}
