package com.tjpu22t01.scaapplepro;

import com.tjpu22t01.scaapplepro.service.CustomInOutService;
import com.tjpu22t01.scaapplepro.service.impl.CustomInOutServiceImpl;
import org.hibernate.annotations.Source;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ScaAppleproApplicationTests {

    CustomInOutService customInOutService = new CustomInOutServiceImpl();

    @Test
    void contextLoads() {
//        System.out.println(customInOutService.registerByPhone("+8613165105153")); // true
//        System.out.println(customInOutService.registerByPhone("105153")); // true
    }

}
