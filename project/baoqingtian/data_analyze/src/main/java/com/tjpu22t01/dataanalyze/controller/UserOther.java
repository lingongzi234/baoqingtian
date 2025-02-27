package com.tjpu22t01.dataanalyze.controller;

import com.tjpu22t01.dataanalyze.feign.ScaAppleproClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("logic/feign")
@RestController
public class UserOther {
@Resource
        private ScaAppleproClient client ;

@RequestMapping("currentUser")
        public String  currentUser() {
    String a = client.sayHello();
    return a;
        }
}
