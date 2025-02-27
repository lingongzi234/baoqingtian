package com.tjpu22t01.respondent.controller;

import cn.dev33.satoken.util.SaResult;
import com.tjpu22t01.respondent.service.RespondentCasesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("logic/respondent")
@Slf4j
public class RespondentCases {
    @Resource
    private RespondentCasesService respondentCasesService;
    @GetMapping("/case")
    public SaResult getcase(@RequestParam("id") Long id){
        return  respondentCasesService.getcase(id);
    }
    @GetMapping("/information")
    public SaResult getcaseinformation(@RequestParam("case_id") long case_id){

        return respondentCasesService.getinformation(case_id);
    }
}
