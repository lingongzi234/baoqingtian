package com.tjpu22t01.respondent.service;

import cn.dev33.satoken.util.SaResult;

public interface RespondentChooseService {
    public SaResult respondentChoose(Integer case_id,int times);
    public SaResult getplan(Integer case_id);
}
