package com.tjpu22t01.dataanalyze.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "sca-gm")
public interface ScaGmClient {

    public String sayHello();
}
