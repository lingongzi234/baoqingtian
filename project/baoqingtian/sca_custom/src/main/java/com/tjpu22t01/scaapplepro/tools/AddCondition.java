package com.tjpu22t01.scaapplepro.tools;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tjpu22t01.scaapplepro.pojo.Condition;

import java.sql.Wrapper;

public class AddCondition {
    public static QueryWrapper addCondition (QueryWrapper wrapper, Condition condition){
        if(condition.getCaseReason() != null && !condition.getCaseReason().isEmpty())
            wrapper.eq("case_reason", condition.getCaseReason());
        if(condition.getStatus() != null && !condition.getStatus().isEmpty())
            wrapper.eq("status", condition.getStatus());
        if(condition.getRespondentIdNumber() != null && !condition.getRespondentIdNumber().isEmpty())
            wrapper.eq("respondent_id_number", condition.getRespondentIdNumber());
        if(condition.getMediatorStatus() != null && !condition.getMediatorStatus().isEmpty())
            wrapper.eq("mediation_status", condition.getMediatorStatus());
        if(condition.getName() != null && !condition.getName().isEmpty())
            wrapper.eq("name", condition.getName());
        if(condition.getGender() != null && !condition.getGender().isEmpty())
            wrapper.eq("gender", condition.getGender());
        if(condition.getIdCardNumber() != null && !condition.getIdCardNumber().isEmpty())
            wrapper.eq("id_card_number", condition.getIdCardNumber());
        if(condition.getMediatorId() != null && condition.getMediatorId() != 0)
            wrapper.eq("mediator_id", condition.getMediatorId());
        return wrapper;
    }
}
