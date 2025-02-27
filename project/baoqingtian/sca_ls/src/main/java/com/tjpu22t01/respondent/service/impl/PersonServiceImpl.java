package com.tjpu22t01.respondent.service.impl;

import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tjpu22t01.common.entity.ApplicantInfo;
import com.tjpu22t01.common.entity.Mediators;
import com.tjpu22t01.common.entity.RespondentInfo;
import com.tjpu22t01.common.entity.User;
import com.tjpu22t01.respondent.mapper.ApplicantMapper;
import com.tjpu22t01.respondent.mapper.MediatorMapper;
import com.tjpu22t01.respondent.mapper.UserMapper;
import com.tjpu22t01.respondent.mapper.RespondentMapper;
import com.tjpu22t01.respondent.pojo.Users;
import com.tjpu22t01.respondent.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
    public class PersonServiceImpl implements PersonService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RespondentMapper respondentMapper;
    @Resource
    private ApplicantMapper applicantMapper;
    @Resource
    private MediatorMapper mediatorMapper;

    @Override
    public SaResult getuser(int user_id,int type){
        log.info("get user id:{}",user_id);
        if(type==0){
            log.info("get user id:{}",user_id);
            Wrapper<User> userWrapper = new QueryWrapper<User>()
                    .eq("user_id", user_id);
            List<User> userList = userMapper.selectList(userWrapper);
            return SaResult.ok().setData(userList);
        }
        if(type==1){
            log.info("get applicant id:{}",user_id);
            Wrapper<ApplicantInfo> applicantWrapper = new QueryWrapper<ApplicantInfo>()
                    .eq("user_id", user_id);
            List<ApplicantInfo> applicantList = applicantMapper.selectList(applicantWrapper);
            return SaResult.ok().setData(applicantList);
        }
        if(type==2){
            log.info("get respondent id:{}",user_id);
            Wrapper<RespondentInfo> RespondentWrapper = new QueryWrapper<RespondentInfo>()
                    .eq("user_id", user_id);
            List<RespondentInfo> RespondentList = respondentMapper.selectList(RespondentWrapper);
            return SaResult.ok().setData(RespondentList);
        }
        if(type==3){
            log.info("get mediators id:{}",user_id);
            Wrapper<Mediators> MediatorWrapper = new QueryWrapper<Mediators>()
                    .eq("user_id", user_id);
            List<Mediators> MediatorList = mediatorMapper.selectList(MediatorWrapper);
            return SaResult.ok().setData(MediatorList);
        }
        return SaResult.ok().setData(null);
    }
    @Override
    public SaResult updategetuser(Users users ){
        log.info("update user");
        log.info(users.toString());
        if(users.getType()==0){
            Wrapper<User> userWrapper = new QueryWrapper<User>()
                    .eq("user_id", users.getUser().getUserId());
            User user = userMapper.selectOne(userWrapper);
            user.setName(users.getUser().getName());
            user.setPasswordHash(users.getUser().getPhoneNumber());
            user.setEmail(users.getUser().getEmail());
            user.setIdCard(users.getUser().getIdCard());
            userMapper.update(user, new UpdateWrapper<User>().eq("user_id", users.getUser().getUserId()));
            return SaResult.ok().setData(users.getUser());
        }
        if(users.getType()==1){
            applicantMapper.update(users.getApplicantInfo(),new UpdateWrapper<ApplicantInfo>().eq("user_id", users.getUser().getUserId()));
            return SaResult.ok().setData(users.getApplicantInfo());
        }
        if(users.getType()==2){
            respondentMapper.update(users.getRespondentInfo(), new UpdateWrapper<RespondentInfo>().eq("user_id", users.getUser().getUserId()));
            return SaResult.ok().setData(users.getRespondentInfo());
        }
        if(users.getType()==3){
            mediatorMapper.update(users.getMediators(),new UpdateWrapper<Mediators>().eq("user_id", users.getUser().getUserId()));
            return SaResult.ok().setData(users.getMediators());
        }
        return SaResult.ok().setData(null);
    }



}
