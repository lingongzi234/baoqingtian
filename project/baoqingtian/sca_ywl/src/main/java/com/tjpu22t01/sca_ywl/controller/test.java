package com.tjpu22t01.sca_ywl.controller;

import cn.dev33.satoken.util.SaResult;

import com.tjpu22t01.sca_ywl.feign.ScaAppleproClient;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("logic/path")
@Log
public class test {
    @Resource
    private ScaAppleproClient scaAppleproClient;
    @RequestMapping("applepro")
    public SaResult applepro() {
        log.info(scaAppleproClient.sayHello());
        return SaResult.data(scaAppleproClient.sayHello());
    }
}
