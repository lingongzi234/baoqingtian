package com.tjpu22t01.scaapplepro.service;

import cn.dev33.satoken.util.SaResult;
import com.tjpu22t01.common.entity.Cases;
import com.tjpu22t01.scaapplepro.pojo.Condition;

public interface CasesService {
    SaResult addCase(Cases cases);

    SaResult updateCase(Cases cases);

//    SaResult getTemporaryCases(int userId);

    SaResult conditionSelect(int userId, Condition condition);

    SaResult getCase(String caseId);

    SaResult caseAddPlan(String caseId, String planDetails);

    SaResult getPlans(String caseId);
}
