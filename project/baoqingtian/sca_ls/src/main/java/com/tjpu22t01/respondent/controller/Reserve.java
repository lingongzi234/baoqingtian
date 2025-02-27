package com.tjpu22t01.respondent.controller;

import cn.dev33.satoken.util.SaResult;
import com.tjpu22t01.common.entity.RespondentInfo;
import com.tjpu22t01.respondent.pojo.Respondent;
import com.tjpu22t01.respondent.service.ReserveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("logic/assistant")
@Slf4j
public class Reserve {
    @Resource
    private ReserveService reserveService;
    @PostMapping("/reserve")
    public SaResult reserve(@RequestBody Respondent respondent) {


        return reserveService.reserve(respondent.getCase_id(),respondent.getId_card(),respondent.getTime());


    }
    @PostMapping("/add_respondent")
    public SaResult addRespondent(@RequestBody Respondent respondent) {
        return reserveService.addRespondent(respondent.getPhone_number(),respondent.getRespondent_info());




    }



}

