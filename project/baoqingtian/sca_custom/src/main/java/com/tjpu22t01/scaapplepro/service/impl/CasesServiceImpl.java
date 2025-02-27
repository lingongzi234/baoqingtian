package com.tjpu22t01.scaapplepro.service.impl;

import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjpu22t01.common.entity.CaseMediationPlans;
import com.tjpu22t01.common.entity.Cases;
import com.tjpu22t01.scaapplepro.mapper.ApplicantMapper;
import com.tjpu22t01.scaapplepro.mapper.CaseMediationPlanMapper;
import com.tjpu22t01.scaapplepro.mapper.CasesMapper;
import com.tjpu22t01.scaapplepro.pojo.Condition;
import com.tjpu22t01.scaapplepro.service.CasesService;
import com.tjpu22t01.scaapplepro.tools.AddCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class CasesServiceImpl implements CasesService {

    @Resource
    CasesMapper casesMapper;

    @Resource
    ApplicantMapper applicantMapper;

    @Resource
    CaseMediationPlanMapper caseMediationPlanMapper;

    @Override
    public SaResult addCase(Cases cases) {


        log.info("adding cases");
        if(cases == null)
            return SaResult.code(505).setMsg("empty data");

        cases.setRespondentId(null);
        cases.setMediatorId(null);
        cases.setOrganizationId(null);

        cases.setStatus(1);
        try{
            log.info("");
            casesMapper.insert(cases);
            log.info("");
            return SaResult.ok().setData(cases);
        }catch(Exception e){
            log.error(e.getMessage());
            return SaResult.code(505).setMsg("data error");
        }

    }

    @Override
    public SaResult updateCase(Cases cases) {

        log.info("updating cases");

        if(cases == null)
            return SaResult.code(505).setMsg("empty data");

        try{
            log.info("");
            Cases flag = casesMapper.selectOne(new QueryWrapper<Cases>()
                    .eq("case_id", cases.getCaseId()));
            log.info("");
            cases.setStatus(1);
            if(flag == null)
                return addCase(cases);
            casesMapper.update(cases, new UpdateWrapper<Cases>().eq("case_id", cases.getCaseId()));
            log.info("");
            return SaResult.ok().setData(cases);
        }catch(Exception e){
            log.error(e.getMessage());
            return SaResult.code(505).setMsg("data error");
        }
    }

//    @Override
//    public SaResult getTemporaryCases(int userId) {
//
//        log.info("getting temporary cases");
//        try{
//            log.info("");
//            Wrapper<Cases> casesWrapper = new QueryWrapper<Cases>()
//                    .eq("applicant_id", userId)
//                    .eq("status", 0);
//            List<Cases> casesList = casesMapper.selectList(casesWrapper);
//            log.info("");
//            return SaResult.ok().setData(casesList);
//        }catch (Exception e){
//            log.error(e.getMessage());
//            return SaResult.code(505).setMsg("data error");
//        }
//    }

    @Override
    public SaResult conditionSelect(int userId, Condition condition) {

//        log.info(condition.toString());

        Page<Cases> page = new Page<>(condition.getCurrent(), condition.getSize());

        QueryWrapper<Cases> wrapper = new QueryWrapper<Cases>()
                .or(orWrapper -> orWrapper.eq("applicant_id", userId).or().eq("respondent_id", userId));
        AddCondition.addCondition(wrapper, condition);

        try{
            IPage<Cases> cases = casesMapper.selectPage(page, wrapper);
            return SaResult.ok().setData(cases);
        }catch (Exception e){
            log.error(e.getMessage());
            return SaResult.error().setMsg("select failed");
        }
    }

    @Override
    public SaResult getCase(String caseId) {

        Wrapper<Cases> wrapper = new QueryWrapper<Cases>().eq("case_id", caseId);

        try{
            Cases oneCase = casesMapper.selectOne(wrapper);
            return SaResult.ok().setData(oneCase);
        }catch (Exception e){
            log.error(e.getMessage());
            return SaResult.error().setMsg("select failed");
        }
    }

    @Override
    public SaResult caseAddPlan(String caseId, String planDetails) {

        CaseMediationPlans plan = new CaseMediationPlans();
        plan.setCaseId(Integer.valueOf(caseId));
        plan.setPlanDetails(planDetails);
        try{
            caseMediationPlanMapper.insert(plan);
            return SaResult.ok().setData(plan);
        }catch(Exception e){
            log.error(e.getMessage());
            return SaResult.error().setMsg("create failed");
        }
    }

    @Override
    public SaResult getPlans(String caseId) {
        Wrapper<CaseMediationPlans> wrapper = new QueryWrapper<CaseMediationPlans>().eq("case_id", caseId);
        try{
            List<CaseMediationPlans> list = caseMediationPlanMapper.selectList(wrapper);
            return SaResult.ok().setData(list);
        }catch (Exception e) {
            log.error(e.getMessage());
            return SaResult.error("select wrong");
        }
    }
}
