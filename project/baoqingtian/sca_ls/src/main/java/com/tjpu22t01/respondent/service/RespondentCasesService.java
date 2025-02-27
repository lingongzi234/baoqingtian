package com.tjpu22t01.respondent.service;

import cn.dev33.satoken.util.SaResult;

public interface RespondentCasesService {
    public SaResult getcase(Long id);
    public SaResult getinformation(long case_id);
}
