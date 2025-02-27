package com.tjpu22t01.respondent.service;

import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestParam;

public interface StatisticsService {
    public SaResult getmonth(int year);
    public SaResult getstatus();
    public  SaResult getinformations(int type);
}
