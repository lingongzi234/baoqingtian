package com.tjpu22t01.scaapplepro.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.tjpu22t01.common.entity.ApplicantInfo;
import com.tjpu22t01.common.entity.User;
import com.tjpu22t01.scaapplepro.mapper.ApplicantMapper;
import com.tjpu22t01.scaapplepro.mapper.CustomInOutMapper;
import com.tjpu22t01.scaapplepro.service.CustomInOutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

import static com.tjpu22t01.scaapplepro.tools.Check.checkPhone;

@Service
@Slf4j
public class CustomInOutServiceImpl implements CustomInOutService {

    @Resource
    CustomInOutMapper customInOutMapper;
    @Resource
    private ApplicantMapper applicantMapper;

    @Override
    public SaResult registerByPhone(String phone, String password) {
        log.info("Phone Number {} register", phone);

        SaResult phoneValid = checkPhone(phone);
        if(phoneValid != null) {
            return phoneValid;
        }
        try{
            log.info("");
            List<User> users = customInOutMapper.selectList(new QueryWrapper<User>().eq("phone_number", phone));
            log.info("");
            if(!users.isEmpty()){
                return SaResult.code(502).setMsg("used phone number");
            }
        }catch (Exception e){
            log.error(e.getCause().getMessage());
        }

        User user = new User();
        user.setPhoneNumber(phone);
        user.setName("用户" + phone);
        user.setPasswordHash(SaSecureUtil.sha256(password));
        user.setIdentityType("1");
        try {
            log.info("");
            int tip = customInOutMapper.insert(user);
            log.info("");
            User ans = customInOutMapper.selectOne(new QueryWrapper<User>().eq("phone_number", phone));
            log.info("");
            ans.setPasswordHash("");
            return tip == 1 ? SaResult.ok().setMsg(phone).setData(ans) : SaResult.error();
        }catch (Exception e){
            return SaResult.error(e.getCause().getMessage());
        }
    }

    @Override
    public SaResult login(String phone, String password) {

        try {
            log.info("");
            User user = customInOutMapper.selectOne(
                    new QueryWrapper<User>()
                            .eq("phone_number", phone)
                            .eq("password_hash", SaSecureUtil.sha256(password)
                            )
            );
            log.info("");
            if(user != null){
                StpUtil.login(user.getUserId());
                StpUtil.getSession().set("User", user);
                String token = StpUtil.getTokenValue();
                user.setPasswordHash("");
                return SaResult.code(200).setData(user).set("token",token);
            }else{
                return SaResult.code(505).setMsg("wrong username or password");
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return SaResult.error(e.getCause().getMessage());
        }
    }

    @Override
    public SaResult changePassword(String phone, String password) {

        try{
            User user = customInOutMapper.selectOne(
                    new QueryWrapper<User>()
                            .eq("phone_number", phone));
            user.setPasswordHash(SaSecureUtil.sha256(password));
            int tip = customInOutMapper.update(user, new UpdateWrapper<User>().eq("phone_number", phone));
            user.setPasswordHash("");
            return SaResult.ok().setData(user);
        }catch (Exception e){
            return SaResult.error(e.getMessage());
        }

    }

    @Override
    public SaResult updateInfo(ApplicantInfo applicantInfo, User user) {

        try{
            log.info("");
            ApplicantInfo app = applicantMapper.selectOne(new QueryWrapper<ApplicantInfo>()
                    .eq("id_card_number", applicantInfo.getIdCardNumber()));
            log.info("");
            if(app == null){
                return applicantMapper.insert(applicantInfo) == 1 ?
                        SaResult.ok().setData(applicantInfo) :
                        SaResult.error("insert failed");
            }
            applicantMapper.update(applicantInfo, new UpdateWrapper<ApplicantInfo>()
                    .eq("id_card_number", applicantInfo.getIdCardNumber()));
            user.setStatus(true);
            customInOutMapper.update(user, new UpdateWrapper<User>()
                    .eq("user_id", user.getUserId()));
            applicantInfo.setUserId(0);
            applicantInfo.setApplicantId(0);
            return SaResult.ok().setData(applicantInfo);
        }catch (Exception e){
            log.error(e.getMessage());
            return SaResult.error("insert or update failed");
        }
    }

}
