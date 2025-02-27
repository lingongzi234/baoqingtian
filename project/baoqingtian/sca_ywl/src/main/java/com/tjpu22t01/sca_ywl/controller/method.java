package com.tjpu22t01.sca_ywl.controller;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.util.SaResult;
import com.tjpu22t01.common.entity.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static cn.dev33.satoken.SaManager.log;

@Controller
@RequestMapping("logic/path/")
public class method {
    @RequestMapping("/ywlString")
    public String ywlmethod1(@RequestParam("param") String param) {
        log.info("This is the String param" + param);
        return "This is the String param" + param;
    }

    @RequestMapping("/ywl/SaResult")
    public SaResult ywlmethod2(@RequestParam("param") int param) {
        SysUser Sysuser = null;
        if(param == 1){
            Sysuser = new SysUser();
            Sysuser.setId(1);
            Sysuser.setUsername("admin");
            Sysuser.setPassword(SaSecureUtil.md5("123456"));
        }
        return SaResult.data(Sysuser);
}
    @RequestMapping("/ywl/SysUser")
    public SaResult ywlmethod3(@RequestBody SysUser User ) {
    return SaResult.data(User);
    }
}


