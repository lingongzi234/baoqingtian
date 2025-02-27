package com.tjpu22t01.respondent.controller;

import cn.dev33.satoken.util.SaResult;
import com.tjpu22t01.respondent.service.StatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("logic/statistics")
public class Statistics {
    @Resource
    private StatisticsService statisticsService;
    @GetMapping("/get_month")
    public SaResult getmonth(@RequestParam int year) {
        return statisticsService.getmonth(year);

    }
    @GetMapping("get_status")
    public SaResult getstatus() {
        return statisticsService.getstatus();
    }
    @GetMapping("get_informations")
    public SaResult getinformations(@RequestParam int type) {
        return statisticsService.getinformations( type);
    }

}
