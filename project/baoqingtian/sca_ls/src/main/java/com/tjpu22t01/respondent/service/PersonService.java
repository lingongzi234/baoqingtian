package com.tjpu22t01.respondent.service;

import cn.dev33.satoken.util.SaResult;
import com.tjpu22t01.common.entity.RespondentInfo;
import com.tjpu22t01.common.entity.User;
import com.tjpu22t01.respondent.pojo.Users;

public interface PersonService {

    public SaResult getuser(int user_id,int type);
    public SaResult updategetuser(Users users );
}
