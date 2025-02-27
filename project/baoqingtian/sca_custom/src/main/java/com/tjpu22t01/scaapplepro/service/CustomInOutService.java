package com.tjpu22t01.scaapplepro.service;

import cn.dev33.satoken.util.SaResult;
import com.tjpu22t01.common.entity.ApplicantInfo;
import com.tjpu22t01.common.entity.User;

public interface CustomInOutService {
    public SaResult registerByPhone(String phone, String password);

    public SaResult login(String phone, String password);

    public SaResult changePassword(String phone, String password);

    public SaResult updateInfo(ApplicantInfo applicantInfo, User user);
}
