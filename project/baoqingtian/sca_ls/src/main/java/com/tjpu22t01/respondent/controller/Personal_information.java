package com.tjpu22t01.respondent.controller;


import cn.dev33.satoken.util.SaResult;
import com.tjpu22t01.common.entity.RespondentInfo;
import com.tjpu22t01.common.entity.User;
import com.tjpu22t01.respondent.pojo.Users;
import com.tjpu22t01.respondent.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("logic/respondent/personal_information")
@Slf4j
public class Personal_information {
    @Resource
    private PersonService personService;

    @GetMapping("get_user")
    public SaResult getuser(@RequestParam("user_id")int user_id,@RequestParam("type") int type) {
        return personService.getuser(user_id,type);
    }
    @PostMapping("update_user")
    public SaResult updateuser(@RequestBody Users users) {
        return personService.updategetuser(users);

    }











}
