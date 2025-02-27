package com.tjpu22t01.scaapplepro.controller;

import cn.dev33.satoken.util.SaResult;
import com.tjpu22t01.scaapplepro.pojo.Condition;
import com.tjpu22t01.scaapplepro.service.MediatorService;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("logic/mediator")
public class Mediator {
    @Resource
    MediatorService mediatorService;

    @GetMapping("getCases")
    public SaResult getCases(@RequestParam String status) {
        return mediatorService.getCases(status);
    }
}
