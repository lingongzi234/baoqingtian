package com.tjpu22t01.respondent.controller;


import cn.dev33.satoken.util.SaResult;
import com.tjpu22t01.respondent.pojo.Choose;
import com.tjpu22t01.respondent.service.RespondentChooseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("logic/respondent")
@Slf4j
public class RespondentChoose {
    @Resource
    private RespondentChooseService respondentChooseService;
    @PostMapping("/choose")
    public SaResult respondentChoose(@RequestBody Choose choose) {

        return respondentChooseService.respondentChoose(choose.getCase_id(),choose.getTimes());

    }
    @GetMapping("/plan")
    public SaResult getplan(@RequestParam Integer case_id) {
        return respondentChooseService.getplan(case_id);
    }











}
