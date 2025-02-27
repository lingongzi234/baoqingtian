package com.tjpu22t01.scaapplepro.service;

import cn.dev33.satoken.util.SaResult;
import com.tjpu22t01.common.entity.Mediators;
import com.tjpu22t01.scaapplepro.pojo.Condition;


public interface AdministratorService {
    SaResult addMediator(Mediators mediator);

    SaResult caseConditionSelect(Condition condition);

    SaResult selectMediator(Condition condition);

    SaResult accessCase(String caseId);

    SaResult caseGetMediator(String caseId, String mediatorId);
}
