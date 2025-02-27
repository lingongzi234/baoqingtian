package com.tjpu22t01.scaapplepro.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.tjpu22t01.common.entity.ApplicantInfo;
import com.tjpu22t01.common.entity.User;
import com.tjpu22t01.scaapplepro.service.CustomInOutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.tjpu22t01.scaapplepro.tools.Check.checkPhone;

@RestController
@RequestMapping("logic/inout")
@Slf4j
public class CustomInOut {

    @Resource
    CustomInOutService customInOutService;


    @PostMapping("updateInfo")
    public SaResult updateInfo(@RequestBody ApplicantInfo applicantInfo) {

        User user = (User) StpUtil.getSession().getDataMap().get("User");
        log.info("user {} changing info", user.getPhoneNumber());
        applicantInfo.setUserId(user.getUserId());

        return customInOutService.updateInfo(applicantInfo,user);
    }

    @GetMapping("logout")
    public SaResult logout() {


        if(StpUtil.isLogin()){
            String info = StpUtil.getLoginId().toString();
            log.info("{} logout", info);
            StpUtil.logout();
            return SaResult.ok().setMsg(info);
        }
        return SaResult.ok().setMsg("not login");

    }

    @PostMapping("changePassword")
    public SaResult changePassword(@RequestParam String phone, @RequestParam String password) {

        log.info("{} change password", phone);

        if(password.length() < 6 || phone.length() != 11) {
            return SaResult.code(504).setMsg("invalid password");
        }

        return customInOutService.changePassword(phone, password);
    }

    @GetMapping("open/login")
    public SaResult login(@RequestParam("phone") String phone, @RequestParam("password") String password) {

        log.info("user {} login", phone);


        if(phone.length() != 11 || password.length() < 6) {
            return SaResult.code(503).setMsg("invalid phone or password");
        }
        return customInOutService.login(phone, password);
    }

    @PostMapping("open/registerByPhone")
    public SaResult registerByPhone(@RequestParam("phone") String phone, String password){

        log.info("{} registering", phone);

        return checkPhone(phone) == null ? customInOutService.registerByPhone(phone, password) : checkPhone(phone);

    }

}
