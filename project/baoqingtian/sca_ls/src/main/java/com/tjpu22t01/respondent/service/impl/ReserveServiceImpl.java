package com.tjpu22t01.respondent.service.impl;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import cn.dev33.satoken.util.SaResult;
import com.tjpu22t01.common.entity.Cases;
import com.tjpu22t01.common.entity.RespondentInfo;
import com.tjpu22t01.common.entity.User;
import com.tjpu22t01.respondent.controller.Reserve;
import com.tjpu22t01.respondent.mapper.CaseMapper;
import com.tjpu22t01.respondent.mapper.RespondentMapper;
import com.tjpu22t01.respondent.mapper.UserMapper;
import com.tjpu22t01.respondent.service.ReserveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class ReserveServiceImpl implements ReserveService {
    @Resource
    private CaseMapper casesMapper;
    @Resource
    private RespondentMapper respondentMapper;
    @Resource
    private UserMapper userMapper;
    @Override
    public SaResult reserve(long case_id,String id_card,String time) {
        log.info("reserve");
        Wrapper<User> userWrapper = new QueryWrapper<User>()
                .eq("id_card",id_card );
        User user =userMapper.selectOne(userWrapper);


        Wrapper<Cases> wrapper = new QueryWrapper<Cases>()
                .eq("case_id",case_id);
        Cases cases = casesMapper.selectOne(wrapper);
        cases.setRespondentId((long)user.getUserId());
        cases.setAppointmentTime(time);
        cases.setMediationStatus(2);
        casesMapper.update(cases,new UpdateWrapper<Cases>().eq("case_id",case_id));
        return SaResult.ok().setData(cases);

    }
    public SaResult addRespondent(String phone_number, RespondentInfo respondentInfo){
        log.info("addRespondent");
        Wrapper<User> userWrapper = new QueryWrapper<User>()
                .eq("phone_number",phone_number );
        User user =userMapper.selectOne(userWrapper);
        respondentInfo.setUserId(user.getUserId());
        respondentMapper.insert(respondentInfo);
        return SaResult.ok().setData(respondentInfo);



    }


}
