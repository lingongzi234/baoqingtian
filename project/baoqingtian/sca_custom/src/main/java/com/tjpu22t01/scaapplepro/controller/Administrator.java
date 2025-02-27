package com.tjpu22t01.scaapplepro.controller;

import cn.dev33.satoken.util.SaResult;
import com.tjpu22t01.common.entity.Mediators;
import com.tjpu22t01.scaapplepro.pojo.Condition;
import com.tjpu22t01.scaapplepro.service.AdministratorService;
import com.tjpu22t01.scaapplepro.tools.Check;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping("logic/administrator")
@RestController
@Slf4j
public class Administrator {

    @Resource
    private AdministratorService administratorService;

    @GetMapping("caseGetMediator")
    public SaResult caseGetMediator(@RequestParam String caseId, @RequestParam String mediatorId) {
       return administratorService.caseGetMediator(caseId, mediatorId);
    }

    @PostMapping("accessCase")
    public SaResult accessCase(@RequestBody String caseId){

        return administratorService.accessCase(caseId);
    }

    @PostMapping("caseConditionSelect")
    public SaResult caseConditionSelect(@RequestBody Condition condition){

        return administratorService.caseConditionSelect(condition);
    }

    @PostMapping("selectMediator")
    public SaResult selectMediator(@RequestBody Condition condition) {

        return administratorService.selectMediator(condition);

    }

    @PostMapping("addMediator")
    public SaResult addMediator(@RequestBody Mediators mediator){
        if(Check.checkMediator(mediator))
            return administratorService.addMediator(mediator);
        return SaResult.code(504).setMsg("data empty");
    }

}
