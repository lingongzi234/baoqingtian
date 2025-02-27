package com.tjpu22t01.respondent.service;

import cn.dev33.satoken.util.SaResult;
import com.tjpu22t01.common.entity.RespondentInfo;

public interface ReserveService {
    public SaResult reserve(long case_id,String id_card,String time);
    public SaResult addRespondent(String phone_number, RespondentInfo respondentInfo);
}
