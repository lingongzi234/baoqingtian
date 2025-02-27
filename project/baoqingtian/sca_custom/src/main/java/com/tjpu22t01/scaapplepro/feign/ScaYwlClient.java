package com.tjpu22t01.scaapplepro.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "sca-ywl")
public interface ScaYwlClient {

    @GetMapping("logic/path//ywlString")
    public String ywlmethod1(@RequestParam("param") String param);
}
