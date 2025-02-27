package com.tjpu22t01.scaapplepro.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.tjpu22t01.common.entity.Cases;
import com.tjpu22t01.common.entity.User;
import com.tjpu22t01.scaapplepro.pojo.Condition;
import com.tjpu22t01.scaapplepro.service.CasesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("logic/cases")
public class CasesAdminister {

    @Resource
    private CasesService casesService;

    @GetMapping("getCases")
    public SaResult getCase(@RequestParam String caseId) {
        return casesService.getCase(caseId);
    }

    @PostMapping("conditionSelect")
    public SaResult conditionSelect(@RequestBody Condition conditions) {
        int userId = ((User) StpUtil.getSession().getDataMap().get("User")).getUserId();
        return casesService.conditionSelect(userId, conditions);
    }

    @PostMapping("addCase")
    public SaResult addCase(@RequestBody Cases cases) {
        int userId = ((User) StpUtil.getSession().getDataMap().get("User")).getUserId();
        cases.setApplicantId(userId);
        return casesService.addCase(cases);
    }

    @PostMapping("updateCase")
    public SaResult updateCase(@RequestBody Cases cases){
        return casesService.updateCase(cases);
    }

    @GetMapping("caseAddPlan")
    public SaResult caseAddPlan(@RequestParam String caseId, @RequestParam String planDetails ) {

        return casesService.caseAddPlan(caseId, planDetails);
    }

    @GetMapping("getPlans")
    public SaResult getPlans(@RequestParam String caseId) {
        return casesService.getPlans(caseId);
    }
}
//    @GetMapping("getTemporaryCases")
//    public SaResult getTemporaryCases(){
//        int userId = ((User) StpUtil.getSession().getDataMap().get("User")).getUserId();
//        return casesService.getTemporaryCases(userId);
//    }
