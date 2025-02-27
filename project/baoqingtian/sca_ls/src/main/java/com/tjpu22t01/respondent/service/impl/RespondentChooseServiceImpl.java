package com.tjpu22t01.respondent.service.impl;

import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tjpu22t01.common.entity.CaseMediationPlans;
import com.tjpu22t01.common.entity.Cases;
import com.tjpu22t01.common.entity.User;
import com.tjpu22t01.respondent.mapper.CaseMapper;
import com.tjpu22t01.respondent.mapper.PlanMapper;
import com.tjpu22t01.respondent.service.RespondentChooseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class RespondentChooseServiceImpl implements RespondentChooseService {
    @Resource
    private PlanMapper planMapper;
    @Resource
    private CaseMapper caseMapper;
    @Override
    public SaResult getplan(Integer case_id) {
        log.info("select plan");
        Wrapper<CaseMediationPlans> planWrapper = new QueryWrapper<CaseMediationPlans>()
                .eq("case_id", case_id);
        List<CaseMediationPlans> planList = planMapper.selectList(planWrapper);
        return SaResult.ok().setData(planList);

    }
    public SaResult respondentChoose(Integer case_id,int times){
        log.info("respondentChoose");
        if (times==-1) {
            Wrapper<Cases> wrapper = new UpdateWrapper<Cases>().eq("case_id", case_id);
            Cases cases = caseMapper.selectOne(wrapper);
            cases.setStatus(4);
            cases.setMediationStatus(4);
            caseMapper.update(cases, new QueryWrapper<Cases>().eq("case_id", case_id));
            return SaResult.ok().setData("1");
        }
        Wrapper<CaseMediationPlans> planwrapper = new UpdateWrapper<CaseMediationPlans>()
                .eq("case_id", case_id)
                .eq("case_plan", times);
        CaseMediationPlans plan=planMapper.selectOne(planwrapper);
        plan.setStatus("已选择");
        planMapper.update(plan,new UpdateWrapper<CaseMediationPlans>().eq("case_id",case_id)
        .eq("case_plan",times));
        Wrapper<Cases> wrapper = new UpdateWrapper<Cases>().eq("case_id", case_id);
        Cases fall=caseMapper.selectOne(wrapper);
        fall.setStatus(3);
        fall.setMediationStatus(3);
        caseMapper.update(fall,new QueryWrapper<Cases>().eq("case_id",case_id));
        return SaResult.ok();

    }
}
